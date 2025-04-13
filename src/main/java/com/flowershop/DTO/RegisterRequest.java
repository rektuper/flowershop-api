package com.flowershop.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String userLogin;

    @NotBlank
    private String userPassword;
}
