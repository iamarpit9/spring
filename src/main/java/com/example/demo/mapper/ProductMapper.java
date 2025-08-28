package com.example.demo.mapper;

import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.Product;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toDto(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock() != null ? product.getStock().getQuantity() : 0,
                product.getCategories().stream()
                        .map(cat -> cat.getName())
                        .collect(Collectors.toList())
        );
    }
}