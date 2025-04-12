package com.flowershop.service;

import com.flowershop.entity.Flower;
import com.flowershop.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    public Flower getFlowerById(Long id) {
        return flowerRepository.findById(id).orElse(null);
    }

    public Flower createFlower(Flower flower) {
        return flowerRepository.save(flower);
    }

    public Flower updateFlower(Long id, Flower flowerDetails) {
        Flower flower = flowerRepository.findById(id).orElseThrow();
        flower.setName(flowerDetails.getName());
        flower.setPrice(flowerDetails.getPrice());
        flower.setStock(flowerDetails.getStock());
        flower.setImageUrl(flowerDetails.getImageUrl());
        return flowerRepository.save(flower);
    }

    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }
}
