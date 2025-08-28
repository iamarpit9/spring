package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private List<String> categories;
}