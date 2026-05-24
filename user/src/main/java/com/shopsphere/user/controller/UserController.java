package com.shopsphere.user.controller;

import com.shopsphere.user.dto.UserRequest;
import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

//private List<User> users =  new ArrayList<>();

private final UserService userService ;
//public UserController(UserService userService) {
//this.userService = userService;
//}


    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return ResponseEntity.ok(userService.fetchAllUsers());
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }


    @PostMapping("/api/users")
    @Transactional
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest) {
       userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/api/users/{id}")
        public ResponseEntity<UserResponse> getUsers(@PathVariable Long id) {
        return userService.fetchUsersById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());


    }
 @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUsers(@PathVariable Long id, @RequestBody UserRequest updatedUser) {
        boolean updated = userService.updateUser(id, updatedUser);
        if(updated)
            return  ResponseEntity.ok("User updated successfully");
        else
            return ResponseEntity.notFound().build();
 }
}
