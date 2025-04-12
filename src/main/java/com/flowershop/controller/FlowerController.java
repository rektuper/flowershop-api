//package com.flowershop.controller;
//
//import com.flowershop.entity.Flower;
//import com.flowershop.service.FlowerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/api/flowers")
//public class FlowerController {
//
//    @Autowired
//    private FlowerService flowerService;
//
//    @GetMapping
//    public List<Flower> getFlowers() {
//        return flowerService.getAllFlowers();
//    }
//
//    @PostMapping
//    public Flower createFlower(@RequestBody Flower flower) {
//        return flowerService.createFlower(flower);
//    }
//
//    @GetMapping("/{id}")
//    public Flower getFlower(@PathVariable Long id) {
//        return flowerService.getFlowerById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Flower updateFlower(@PathVariable Long id, @RequestBody Flower flower) {
//        return flowerService.updateFlower(id, flower);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteFlower(@PathVariable Long id) {
//        flowerService.deleteFlower(id);
//    }
//}