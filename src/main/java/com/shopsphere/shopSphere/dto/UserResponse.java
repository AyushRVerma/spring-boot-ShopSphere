package com.shopsphere.shopSphere.dto;

import com.shopsphere.shopSphere.entity.UserRole;
import lombok.Data;

@Data
public class UserResponse {
//@Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO addressdto;
}
