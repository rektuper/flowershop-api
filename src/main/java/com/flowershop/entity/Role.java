package com.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends BaseEntity implements GrantedAuthority {

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}

