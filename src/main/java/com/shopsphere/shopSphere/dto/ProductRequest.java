package com.shopsphere.shopSphere.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {


    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer stockQuantity;
    private String productImage;
    private String productCategory;

}
