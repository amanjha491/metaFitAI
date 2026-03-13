package com.metaFitAi.userService.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
