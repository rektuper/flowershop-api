package com.flowershop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
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
public class OrderItem extends BaseEntity {
    @ManyToOne
    private Bouquet bouquet;

    private Integer quantity;

    @DecimalMin("0.0")
    private BigDecimal priceAtPurchase;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}