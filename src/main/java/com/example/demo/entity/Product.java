package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private BigDecimal price;

    private String description;

    // Example: Product belongs to multiple categories
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    // 1-to-1 relationship with stock
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Stock stock;

}