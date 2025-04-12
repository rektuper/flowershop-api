package com.flowershop.repository;

import com.flowershop.entity.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long>, JpaSpecificationExecutor<Bouquet> {
}

