package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Stock;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final CategoryRepository categoryRepository;

    // Transaction Example: create product + initial stock
    @Transactional
    public ProductResponse createProductWithStock(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();

        // Save product
        Product savedProduct = productRepository.save(product);

        // Create stock entry
        Stock stock = Stock.builder()
                .quantity(request.getInitialStock())
                .product(savedProduct)
                .build();

        stockRepository.save(stock);

        // Link back (optional)
        savedProduct.setStock(stock);

        return ProductMapper.toDto(savedProduct);
    }
}