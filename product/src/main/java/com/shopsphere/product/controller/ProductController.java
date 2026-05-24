package com.shopsphere.product.controller;

import com.shopsphere.product.dto.ProductRequest;
import com.shopsphere.product.dto.ProductResponse;
import com.shopsphere.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest product) {

        return  productService.updateProduct(productId, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId) {

        boolean deleted =  productService.deleteProduct(productId);
        return  deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
    }
@GetMapping("/search ")
    public ResponseEntity<List<ProductResponse>> searchProduct (@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
//        return ResponseEntity.ok(productService.getAllProducts());
    }
}
