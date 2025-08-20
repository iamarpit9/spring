package com.example.demo.service;

import com.example.demo.entity.ProductDesc;
import com.example.demo.repository.ProductDescRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductDescService {

    private final ProductDescRepository productDescRepository;

    public void addDescription(ProductDesc description) {
        productDescRepository.save(description);
    }


}
