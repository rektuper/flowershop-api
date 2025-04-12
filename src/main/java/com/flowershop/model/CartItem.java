package com.flowershop.model;

public class CartItem {
    private Long flowerId;
    private int quantity;

    // Конструкторы
    public CartItem() {}

    public CartItem(Long flowerId, int quantity) {
        this.flowerId = flowerId;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры
    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
