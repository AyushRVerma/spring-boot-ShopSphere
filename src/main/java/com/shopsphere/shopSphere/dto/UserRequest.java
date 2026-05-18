package com.shopsphere.shopSphere.dto;

import com.shopsphere.shopSphere.model.UserRole;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserRequest {
@Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO addressd;
}
