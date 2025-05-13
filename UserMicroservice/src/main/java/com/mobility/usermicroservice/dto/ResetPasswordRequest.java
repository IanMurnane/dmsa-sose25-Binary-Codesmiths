package com.mobility.usermicroservice.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
}
