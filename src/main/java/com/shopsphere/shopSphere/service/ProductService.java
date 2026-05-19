package com.shopsphere.shopSphere.service;

import com.shopsphere.shopSphere.dto.ProductResponse;
import com.shopsphere.shopSphere.dto.ProductRequest;
import com.shopsphere.shopSphere.entity.Product;
import com.shopsphere.shopSphere.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;



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
//        product.setProductId(productRequest.getProductId());
        product.setStockQuantity(productRequest.getStockQuantity());

    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product =   new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);

    }

    public Optional <ProductResponse> updateProduct(Long productId, ProductRequest productRequest) {
    return productRepository.findById(productId)
            .map(existtingProduct -> {
                updateProductFromRequest(existtingProduct, productRequest);
                Product updatedProduct   = productRepository.save(existtingProduct);
                return mapToProductResponse(updatedProduct);
            });
//            .orElseThrow(() -> new RuntimeException("Product not found: "+ id));


    }

    public List<ProductResponse> getAllProducts() {
//        productRepository.findAll();
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

    }

    public boolean deleteProduct(Long productId) {
//    Product product= productRepository.findById(productId)
//            .orElseThrow(() -> new RuntimeException("Product Not Found"));
//    product.setActive(false);
        return productRepository.findById(productId)
                        .map(product -> {
                            product.setActive(false);
                            productRepository.save(product);
                            return true;
                        }).orElse(false);

    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword)
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
