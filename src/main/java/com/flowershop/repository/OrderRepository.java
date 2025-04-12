//package com.flowershop.repository;
//
//import com.flowershop.entity.Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface OrderRepository extends JpaRepository<Order, Long> {
//
//    List<Order> findByUserId(Long userId);
//    Optional<Order> findById(Long id);
//
//}