package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be > 0")
    private BigDecimal price;

    private String description;

    @Min(0)
    private int initialStock;
}
