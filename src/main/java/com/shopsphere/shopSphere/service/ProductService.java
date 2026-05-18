package com.shopsphere.shopSphere.service;

import com.shopsphere.shopSphere.dto.ProductResponse;
import com.shopsphere.shopSphere.dto.ProductRequest;
import com.shopsphere.shopSphere.model.Product;
import com.shopsphere.shopSphere.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product =   new Product();
    updateProductFromRequest(product, productRequest);
            Product savedProduct = productRepository.save(product);
    return mapToProductResponse(savedProduct);

    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
     ProductResponse productResponse = new ProductResponse();
     productResponse.setProductName(savedProduct.getProductName());
     productResponse.setProductImage(savedProduct.getProductImage());
     productResponse.setProductCategory(savedProduct.getProductCategory());
     productResponse.setActive(savedProduct.getActive());
     productResponse.setProductDescription(savedProduct.getProductDescription());
     productResponse.setProductPrice(savedProduct.getProductPrice());
     productResponse.setProductId(savedProduct.getProductId());
     productResponse.setStockQuantity(savedProduct.getStockQuantity());
     return productResponse;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setProductCategory(productRequest.getProductCategory());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductId(productRequest.getProductId());
        product.setStockQuantity(productRequest.getStockQuantity());

    }
}
