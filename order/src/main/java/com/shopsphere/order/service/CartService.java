package com.shopsphere.order.service;


import com.shopsphere.order.dto.CartRequest;
import com.shopsphere.order.entity.Cart;
//import com.shopsphere.order.entity.Product;
//import com.shopsphere.order.entity.User;
import com.shopsphere.order.repository.CartRepository;
//import com.shopsphere.order.repository.ProductRepository;
//import com.shopsphere.order.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository ;

    public boolean addToCart(String userId, CartRequest cartRequest) {
//        Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());
//
//        if (productOptional.isEmpty())
//            return false;
//
//        Product product = productOptional.get();

//        if (product.getStockQuantity() < cartRequest.getQuantity())
//            return false;
//
//        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
//
//        if (userOptional.isEmpty())
//            return false;
//
//        User user = userOptional.get();

        Cart exisitingCartItem = cartRepository.findByUserIdAndProductId(userId , cartRequest.getProductId());
        if (exisitingCartItem != null){
            //uPDATE the quantity
            exisitingCartItem.setQuantity(exisitingCartItem.getQuantity() + cartRequest.getQuantity());
            exisitingCartItem.setProductPrice(BigDecimal.valueOf(1000.00));
            cartRepository.save(exisitingCartItem);
        }
        else{
            //Create new Cart Item
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(cartRequest.getProductId());
            cartItem.setQuantity(cartRequest.getQuantity());
            cartItem.setProductPrice(BigDecimal.valueOf(1000.00));
            cartRepository.save(cartItem);
        }

    return true;

    }

    public boolean deleteFromItemCart(String userId, Long productId) {

        Cart cartItem = cartRepository.findByUserIdAndProductId(userId,productId);

        if(cartItem != null){
            cartRepository.delete(cartItem);
            return true;
        }
//        Optional<Product> productOptional = productRepository.findById(productId);
//        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));




        return false;

    }

    public List<Cart> getCart(String userId) {
        return cartRepository.findByUserId(userId);

    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);

    }
}
