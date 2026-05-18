package com.shopsphere.shopSphere.repository;

import com.shopsphere.shopSphere.dto.ProductResponse;
import com.shopsphere.shopSphere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

@Query("SELECT p FROM products p WHERE p.active = true AND p.stockQuantity>0" +
        "AND LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
