package com.flowershop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favorite extends BaseEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Bouquet bouquet;
}
