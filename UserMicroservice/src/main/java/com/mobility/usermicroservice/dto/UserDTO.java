package com.mobility.usermicroservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
    private String role;
}
