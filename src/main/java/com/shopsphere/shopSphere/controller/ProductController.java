package com.shopsphere.shopSphere.controller;

import com.shopsphere.shopSphere.dto.ProductRequest;
import com.shopsphere.shopSphere.dto.ProductResponse;
import com.shopsphere.shopSphere.model.Product;
import com.shopsphere.shopSphere.repository.ProductRepository;
import com.shopsphere.shopSphere.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;


//    public ResponseEntity <List<Product>> getAll() {
//        return ResponseEntity.ok(productRepository.findAll());
//    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        return new ResponseEntity<ProductResponse>(productService.addProduct(product),
                HttpStatus.CREATED);
    }

}
