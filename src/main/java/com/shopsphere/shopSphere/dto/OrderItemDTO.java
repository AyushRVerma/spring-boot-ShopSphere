package com.shopsphere.shopSphere.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {


    private Long id;
    private Long productID;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;



//    public OrderItemDTO(Long id, Long productId, BigDecimal price, BigDecimal multiply) {
//    }
}
