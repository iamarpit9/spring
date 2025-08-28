package com.example.demo.controller;

import com.example.demo.dto.ProductRequest;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    // Create product with stock
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProductWithStock(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<ProductResponse> list(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductMapper::toDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }

}