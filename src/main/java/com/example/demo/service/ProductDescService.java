package com.example.demo.service;

import com.example.demo.entity.ProductDesc;
import com.example.demo.repository.ProductDescRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDescService {

    private final ProductDescRepository productDescRepository;

    @Autowired
    public ProductDescService(ProductDescRepository productDescRepository) {
        this.productDescRepository = productDescRepository;
    }

    public void addDescription(ProductDesc description) {
        productDescRepository.save(description);
    }


}
