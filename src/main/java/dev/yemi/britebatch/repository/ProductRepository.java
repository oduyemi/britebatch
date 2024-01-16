package dev.yemi.britebatch.repository;

import dev.yemi.britebatch.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
