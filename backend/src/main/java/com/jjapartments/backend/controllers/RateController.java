package com.jjapartments.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jjapartments.backend.models.Rate;
import com.jjapartments.backend.exception.ErrorException;
import com.jjapartments.backend.repository.RateRepository;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    @Autowired
    private RateRepository rateRepository;

    // Create
    @PostMapping("/add")
    public ResponseEntity<?> addRate(@RequestBody Rate rate) {
        try {
            Rate newRate = rateRepository.add(rate);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRate);
        } catch (ErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        List<Rate> rates = rateRepository.findAll();
        return ResponseEntity.ok(rates);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRate(@PathVariable int id) {
        int rowsAffected = rateRepository.delete(id);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Rate deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rate not found");
        }
    }

    // Update
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRate(@PathVariable int id, @RequestBody Rate rate) {
        try {
            rateRepository.update(id, rate);
            return ResponseEntity.ok(rateRepository.findById(id));
        } catch (ErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/latest/type")
    public ResponseEntity<?> getLatestByType(@RequestParam String type) {
        try {
            Rate rates = rateRepository.findLatestByType(type);
            return ResponseEntity.ok(rates);
        } catch (ErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/type")
    public ResponseEntity<?> getByType(@RequestParam String type) {
        try {
            List<Rate> rates = rateRepository.findByType(type);
            return ResponseEntity.ok(rates);
        } catch (ErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}