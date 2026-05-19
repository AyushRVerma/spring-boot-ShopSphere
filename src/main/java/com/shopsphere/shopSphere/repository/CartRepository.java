package com.shopsphere.shopSphere.repository;

import com.shopsphere.shopSphere.entity.Cart;
import com.shopsphere.shopSphere.entity.Product;
import com.shopsphere.shopSphere.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    Cart findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);

    void deleteByUser(User user);
}
