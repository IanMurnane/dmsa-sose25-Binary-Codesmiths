package com.mobility.usermicroservice.service.impl;

import com.mobility.usermicroservice.dto.ResetPasswordRequest;
import com.mobility.usermicroservice.dto.UserDTO;
import com.mobility.usermicroservice.entity.User;
import com.mobility.usermicroservice.repository.UserRepository;
import com.mobility.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    
     
        // if (!"User Provider".equalsIgnoreCase(userDTO.getRole())) {
        //     throw new IllegalArgumentException("Role must be 'User Provider'");
        // }       

        String role = userDTO.getRole();
        if (role == null || 
           (!role.equalsIgnoreCase("admin") && 
            !role.equalsIgnoreCase("user") && 
            !role.equalsIgnoreCase("provider"))) {
            throw new RuntimeException("Role must be one of: Admin, User, Provider");
        }

        User user = User.builder()
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
                
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
    
        if (userDTO.getPassword() != null && !userDTO.getPassword().equals(existingUser.getPassword())) {
            throw new IllegalArgumentException("Password cannot be updated");
        }
    
     
        if (!existingUser.getEmail().equalsIgnoreCase(userDTO.getEmail()) &&
            userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    
      
        // if (!"User Provider".equalsIgnoreCase(userDTO.getRole())) {
        //     throw new IllegalArgumentException("Role must be 'User Provider'");
        // }
        

        String role = userDTO.getRole();
        if (role == null || 
           (!role.equalsIgnoreCase("admin") && 
            !role.equalsIgnoreCase("user") && 
            !role.equalsIgnoreCase("provider"))) {
            throw new RuntimeException("Role must be one of: Admin, User, Provider");
        }
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());
    
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
  public User login(String email, String password) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!user.getPassword().equals(password)) {
        throw new RuntimeException("Invalid email or password");
    }

    return user; 
  }

  @Override
  public void resetPassword(ResetPasswordRequest request) {
      User user = userRepository.findByEmail(request.getEmail())
              .orElseThrow(() -> new RuntimeException("User not found"));
  
      if (!request.getCurrentPassword().equals(user.getPassword())) {
          throw new RuntimeException("Current password is incorrect");
      }
  
     
      if (request.getNewPassword().equals(user.getPassword())) {
          throw new RuntimeException("New password must be different from current password");
      }
  
      user.setPassword(request.getNewPassword());
      userRepository.save(user);
  }
  

}
