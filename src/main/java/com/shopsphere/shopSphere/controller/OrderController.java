package com.shopsphere.shopSphere.controller;


import com.shopsphere.shopSphere.dto.OrderResponse;
import com.shopsphere.shopSphere.repository.OrderRepository;
import com.shopsphere.shopSphere.service.OrderService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User_ID") String userId){
       return orderService.createOrder(userId)
               .map(orderResponse -> new ResponseEntity<>(orderResponse, HttpStatus.CREATED))
               .orElseGet(()-> ResponseEntity.badRequest().build());

    }
}
