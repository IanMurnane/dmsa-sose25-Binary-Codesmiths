package com.mobility.usermicroservice.dto;

import com.mobility.usermicroservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private User user;
}