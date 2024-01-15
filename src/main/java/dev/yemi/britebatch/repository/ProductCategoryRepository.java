package dev.yemi.britebatch.repository;

import dev.yemi.britebatch.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    // You can add custom queries here if needed
}
