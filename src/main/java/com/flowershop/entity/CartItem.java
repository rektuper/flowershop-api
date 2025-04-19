package com.flowershop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity{
    @ManyToOne
    private User user;

    @ManyToOne
    private Bouquet bouquet;

    private Integer quantity;

    public BigDecimal getTotalPrice() {
        return bouquet.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}

