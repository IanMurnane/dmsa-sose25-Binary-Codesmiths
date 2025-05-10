package com.mobility.usermicroservice.service;

import com.mobility.usermicroservice.dto.ResetPasswordRequest;
import com.mobility.usermicroservice.dto.UserDTO;
import com.mobility.usermicroservice.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    User login(String email, String password);
    void resetPassword(ResetPasswordRequest request);
}
