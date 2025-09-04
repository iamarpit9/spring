package com.example.demo.service;

import com.example.demo.ds.LruCache;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FastProductService {

    private final ProductRepository productRepository;

    // tiny L1 cache for demo; tune as needed
    private LruCache<Long, ProductResponse> cache;

    @PostConstruct
    void init() {
        cache = new LruCache<>(100);
    }

    public Optional<ProductResponse> getProductFast(Long id) {
        ProductResponse cached = cache.get(id);
        if (cached != null) return Optional.of(cached);

        return productRepository.findById(id)
                .map(p -> {
                    ProductResponse dto = ProductMapper.toDto(p);
                    cache.put(id, dto);
                    return dto;
                });
    }

    // for tests
    public int cacheSize() { return cache.size(); }
}
