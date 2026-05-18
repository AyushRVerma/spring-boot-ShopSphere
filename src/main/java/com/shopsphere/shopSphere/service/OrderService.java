package com.shopsphere.shopSphere.service;


import com.shopsphere.shopSphere.dto.OrderItemDTO;
import com.shopsphere.shopSphere.dto.OrderResponse;
import com.shopsphere.shopSphere.model.*;
import com.shopsphere.shopSphere.repository.OrderRepository;
import com.shopsphere.shopSphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;

    public Optional <OrderResponse> createOrder(String userId) {
        //Validate for Cart Items

        List<Cart> cartItems= cartService.getCart(userId);
        if(cartItems.isEmpty()){
            return Optional.empty();
        }
        //Validate for user

        Optional<User> userOptional=userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty()){
return Optional.empty();
        }
      User user=userOptional.get();
        //Calculate total price

        BigDecimal totalPrice= cartItems.stream()
                .map(Cart::getProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Create Order
        Order order=new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalPrice(totalPrice);
        List<OrderItem> orderItems= cartItems.stream()
                .map(item -> new OrderItem(
                                        null,
                                        item.getProduct(),
                        item.getQuantity(),
                        item.getProductPrice(),
                                        order
                                )).toList();

                order.setItems(orderItems);
                Order savedOrder=orderRepository.save(order);


        //Clear the cart
          cartService.clearCart(userId);

          return Optional.of(mapToOrderResponse(savedOrder));

    }

    private OrderResponse mapToOrderResponse(Order order) {
    return  new OrderResponse(
            order.getOrderId(),
            order.getTotalPrice(),
            order.getStatus(),

            order.getItems()
                    .stream()
                    .map(orderItem-> new OrderItemDTO(
                            orderItem.getId(),
                            orderItem.getProduct().getProductId(),
                            orderItem.getQuantity(),
                            orderItem.getPrice(),
                            orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()
                    )))).toList(),
            order.getCreatedAt());


    }
}
