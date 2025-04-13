package com.flowershop.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String userLogin;
    private String userPassword;
}
