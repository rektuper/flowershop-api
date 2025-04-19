package com.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(unique = true)
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}

