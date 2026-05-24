package com.shopsphere.order.repository;

import com.shopsphere.order.entity.Cart;
//import com.shopsphere.order.entity.Product;
//import com.shopsphere.order.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    Cart findByUserIdAndProductId(String userId, Long productId);

    void deleteByUserIdAndProductId(String userId, Long productId);

    List<Cart> findByUserId(String userId);

    void deleteByUserId(String userId);
}
