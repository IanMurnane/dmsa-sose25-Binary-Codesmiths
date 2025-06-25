package com.booking.booking_service.dto;

import com.booking.booking_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private User user;
}