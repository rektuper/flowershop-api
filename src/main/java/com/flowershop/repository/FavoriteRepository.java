package com.flowershop.repository;

import com.flowershop.entity.Bouquet;
import com.flowershop.entity.Favorite;
import com.flowershop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndBouquet(User user, Bouquet bouquet);
    void deleteByUserAndBouquet(User user, Bouquet bouquet);
}
