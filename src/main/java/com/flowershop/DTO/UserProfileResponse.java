package com.flowershop.DTO;


import lombok.Data;

import java.util.List;

@Data
public class UserProfileResponse {
    private Long id;
    private String userLogin;
    private List<String> roles;

    public UserProfileResponse(Long id, String userLogin, List<String> roles) {
        this.id = id;
        this.userLogin = userLogin;
        this.roles = roles;
    }
}
