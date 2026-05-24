package com.shopsphere.order.dto;

import com.shopsphere.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItemDTO> items;
    private LocalDateTime createdAt;

    public OrderResponse(Long orderId, OrderStatus status, BigDecimal totalPrice, List<OrderItemDTO> list) {
    }
}
