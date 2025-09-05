package com.example.demo.service;

import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReplenishmentService {

    private final StockRepository stockRepository;

    public class Item {
        public final Long productId;
        public final int available;
        public final int threshold;
        public final double ratio; // available/threshold

        public Item(Long productId, int available, int threshold) {
            this.productId = productId;
            this.available = available;
            this.threshold = threshold <= 0 ? 10 : threshold;
            this.ratio = (double) available / this.threshold;
        }
    }

    public List<Item> next(int limit) {
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingDouble(i -> i.ratio)); // min-heap
        for (Stock s : stockRepository.findAll()) {
            int threshold = getThresholdOrDefault(s);
            pq.add(new Item(s.getProduct().getId(), s.getQuantity(), threshold));
        }
        List<Item> out = new ArrayList<>();
        while (!pq.isEmpty() && out.size() < limit) out.add(pq.poll());
        return out;
    }

    private int getThresholdOrDefault(Stock s) {
        try {
            var f = s.getClass().getDeclaredField("threshold");
            f.setAccessible(true);
            Object val = f.get(s);
            if (val instanceof Integer i && i > 0) return i;
        } catch (Exception ignored) {}
        return 10;
    }
}
