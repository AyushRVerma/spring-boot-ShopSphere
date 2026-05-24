package com.shopsphere.order.dto;

import lombok.Data;


@Data
public class CartRequest {


    private Long productId;
    private Integer  quantity;


}
