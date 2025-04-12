//package com.flowershop.entity;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> items;
//
//    private double totalPrice;
//
//    public Cart() {}
//
//    public Cart(User user) {
//        this.user = user;
//    }
//
//    public void calculateTotalPrice() {
//        double total = 0;
//        for (CartItem item : items) {
//            total += item.getFlower().getPrice() * item.getQuantity();
//        }
//        this.totalPrice = total;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//   }
//
//    public List<CartItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<CartItem> items) {
//        this.items = items;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//}