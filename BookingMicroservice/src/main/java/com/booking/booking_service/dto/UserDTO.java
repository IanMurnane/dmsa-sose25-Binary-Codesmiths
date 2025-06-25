package com.booking.booking_service.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
    private String role;  // Admin, User, Provider
}
