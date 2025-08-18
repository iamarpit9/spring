package com.example.demo.repository;

        import com.example.demo.entity.ProductDesc;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescRepository extends JpaRepository<ProductDesc, Long> {
}
