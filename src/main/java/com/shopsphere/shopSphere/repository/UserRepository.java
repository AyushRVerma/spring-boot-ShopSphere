package com.shopsphere.shopSphere.repository;

import com.shopsphere.shopSphere.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{




}
