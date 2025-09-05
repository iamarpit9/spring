package com.example.demo.service;

import com.example.demo.ds.Trie;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutocompleteService {

    private final ProductRepository productRepository;
    private Trie trie;

    @PostConstruct
    void build() {
        trie = new Trie(20); // store top 20 ids per node
        for (Product p : productRepository.findAll()) {
            trie.insert(p.getName(), p.getId());
        }
    }

    public List<Long> suggest(String prefix, int k) {
        return trie.query(prefix, k);
    }

    // optional: refresh on demand
    public void indexProduct(Product p) {
        trie.insert(p.getName(), p.getId());
    }
}
