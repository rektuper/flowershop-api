package com.flowershop.repository;

import com.flowershop.entity.Bouquet;
import com.flowershop.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<Flower, Long> {}
