package com.shopsphere.shopSphere.dto;

import lombok.Data;


@Data
public class CartRequest {


    private Long productId;
    private Integer  quantity;


}
