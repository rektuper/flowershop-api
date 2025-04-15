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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bouquet bouquet;

    private Integer quantity;

    public Double getTotalPrice() {
        BigDecimal price = bouquet.getPrice();
        BigDecimal quantityDecimal = BigDecimal.valueOf(quantity);
        return price.multiply(quantityDecimal).doubleValue();
    }
}

