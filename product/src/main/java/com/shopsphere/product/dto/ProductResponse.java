package com.shopsphere.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer stockQuantity;
    private String productImage;
    private String productCategory;
    private Boolean active;
}
