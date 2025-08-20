package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDesc;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Product entities.
 */
@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDescService productDescService;

    /**
     * Save a product.
     *
     * @param product the entity to save
     * @return the persisted entity
     */
    @Transactional
    public Product saveProduct(Product product) {
        Product saveProd = this.productRepository.save(product);

        ProductDesc description = new ProductDesc();

        description.setDescription("description");
        description.setProduct(product);
        this.productDescService.addDescription(description);
        return saveProd;
    }

    /**
     * Get all the products.
     *
     * @return the list of entities
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get one product by ID.
     *
     * @param id the ID of the entity
     * @return the entity
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Update a product.
     *
     * @param id the ID of the entity
     * @param updatedProduct the updated entity
     * @return the updated entity
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    /**
     * Delete the product by ID.
     *
     * @param id the ID of the entity
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}