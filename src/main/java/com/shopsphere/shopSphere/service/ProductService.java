package com.shopsphere.shopSphere.service;

import com.shopsphere.shopSphere.controller.ProductRequest;
import com.shopsphere.shopSphere.dto.ProductResponse;
import com.shopsphere.shopSphere.model.Product;
import com.shopsphere.shopSphere.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest product) {
    return productRepository.save(product);
    }
}
