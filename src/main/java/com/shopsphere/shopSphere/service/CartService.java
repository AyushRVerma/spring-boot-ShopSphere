package com.shopsphere.shopSphere.service;


import com.shopsphere.shopSphere.dto.CartRequest;
import com.shopsphere.shopSphere.model.Cart;
import com.shopsphere.shopSphere.model.Product;
import com.shopsphere.shopSphere.model.User;
import com.shopsphere.shopSphere.repository.CartRepository;
import com.shopsphere.shopSphere.repository.ProductRepository;
import com.shopsphere.shopSphere.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository ;

    public boolean addToCart(String userId, CartRequest cartRequest) {
        Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());

        if (productOptional.isEmpty())
            return false;

        Product product = productOptional.get();

        if (product.getStockQuantity() < cartRequest.getQuantity())
            return false;

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));

        if (userOptional.isEmpty())
            return false;

        User user = userOptional.get();

        Cart exisitingCartItem = cartRepository.findByUserAndProduct(user , product);
        if (exisitingCartItem != null){
            //uPDATE the quantity
            exisitingCartItem.setQuantity(exisitingCartItem.getQuantity() + cartRequest.getQuantity());
            exisitingCartItem.setProductPrice(product.getProductPrice().multiply(BigDecimal.valueOf(exisitingCartItem.getQuantity())));
            cartRepository.save(exisitingCartItem);
        }
        else{
            //Create new Cart Item
            Cart cartItem = new Cart();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartRequest.getQuantity());
            cartItem.setProductPrice(product.getProductPrice().multiply(BigDecimal.valueOf(cartRequest.getQuantity())));
            cartRepository.save(cartItem);
        }

    return true;

    }

    public boolean deleteFromItemCart(String userId, Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));

        if(productOptional.isPresent() && userOptional.isPresent()){
            cartRepository.deleteByUserAndProduct(userOptional.get(), productOptional.get());
             return true;
        }


        return false;

    }
}
