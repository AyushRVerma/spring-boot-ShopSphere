package com.shopsphere.shopSphere.service;


import com.shopsphere.shopSphere.dto.AddressDTO;
import com.shopsphere.shopSphere.dto.UserRequest;
import com.shopsphere.shopSphere.dto.UserResponse;
import com.shopsphere.shopSphere.model.Address;
import com.shopsphere.shopSphere.model.User;
import com.shopsphere.shopSphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

   private UserResponse mapToUserResponse(User user){
       UserResponse userResponse = new UserResponse();
       userResponse.setId(String.valueOf(user.getId()));
       userResponse.setFirstName(user.getFirstName());
       userResponse.setLastName(user.getLastName());
       userResponse.setEmail(user.getEmail());
       userResponse.setPhone(user.getPhone());
       userResponse.setRole(user.getRole());

       if(user.getAddress() != null){
           AddressDTO addressD = new AddressDTO();
             addressD.setStreet(user.getAddress().getStreet());
             addressD.setCity(user.getAddress().getCity());
             addressD.setCountry(user.getAddress().getCountry());
             addressD.setState(user.getAddress().getState());
             addressD.setZipcode(user.getAddress().getZipcode());
             userResponse.setAddressdto(addressD);

       }
       return userResponse;

   }


    public List<UserResponse> fetchAllUsers() {
//        List<User> userList=
        return userRepository
                .findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest) {
//        userRequest.setId(userRequest.getId());
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
User user =  new User();
updateUserFromRequest(user, userRequest);
        userRepository.save(user);

    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
       user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddressd()!=null) {
            Address address = new Address();
            address.setCity(userRequest.getAddressd().getCity());
            address.setState(userRequest.getAddressd().getState());
            address.setZipcode(userRequest.getAddressd().getZipcode());
            address.setStreet(userRequest.getAddressd().getStreet());
            address.setCountry(userRequest.getAddressd().getCountry());
           user.setAddress(address);
        }
    }

    public Optional<UserResponse> fetchUsersById(Long id) {
        return userRepository
                .findById(id)
//                .stream()
                .map(this::mapToUserResponse)
//                .findFirst()
                ;


    }

    public boolean updateUser(Long id, UserRequest updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
//            existingUser.setFirstName(updatedUser.getFirstName());
//            existingUser.setLastName(updatedUser.getLastName());
                            updateUserFromRequest(existingUser, updatedUser);
                            userRepository.save(existingUser);
                            return true;
                        }).orElse(false);

//            userRepository.save(existingUser);
//
//            return true;
//        })
//                .orElse(false);

    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
