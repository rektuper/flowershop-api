package com.flowershop.service;

import com.flowershop.entity.Bouquet;
import com.flowershop.repository.BouquetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BouquetService {
    private final BouquetRepository repo;

    public BouquetService(BouquetRepository repo) {
        this.repo = repo;
    }

    public List<Bouquet> findAll() {
        return repo.findAll();
    }

    public Bouquet save(Bouquet bouquet) {
        return repo.save(bouquet);
    }

    public Bouquet findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Букет не найден"));
    }

    public Bouquet update(Long id, Bouquet updatedBouquet) {
        Bouquet bouquet = findById(id);
        bouquet.setName(updatedBouquet.getName());
        bouquet.setDescription(updatedBouquet.getDescription());
        bouquet.setPrice(updatedBouquet.getPrice());
        bouquet.setInStock(updatedBouquet.isInStock());
        bouquet.setFlowerList(updatedBouquet.getFlowerList());
        return repo.save(bouquet);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

