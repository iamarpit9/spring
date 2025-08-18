package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_desc")
public class ProductDesc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    // Constructors, getters and setters, and other methods...

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public Product getProduct() {
        return product;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
