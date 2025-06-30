package com.booking.booking_service.service;

import com.booking.booking_service.dto.LoginRequest;
import com.booking.booking_service.dto.LoginResponse;
import com.booking.booking_service.dto.UserDTO;
import com.booking.booking_service.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Map;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @PostMapping("/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @PostMapping("/users/register")
    ResponseEntity<Map<String, Long>> registerUser(@RequestBody UserDTO userDTO);

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable("id") Long id);

    @GetMapping("/users")
    ResponseEntity<List<User>> getUser();
}
