package com.jjapartments.backend.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

import com.jjapartments.backend.models.Rate;
//import com.jjapartments.backend.exception.ErrorException; unused
import com.jjapartments.backend.repository.RateRepository;

@Controller
public class AddRateController{
    @Autowired
    private RateRepository rateRepository;

    @GetMapping()
    public String addRate(
        @RequestParam("type") String type,
        @RequestParam("rate") float rateValue,
        @RequestParam("date") String date) {

        List<String> validTypes = List.of("Meralco", "Manila Water");
        if (!validTypes.contains(type)) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid rate type", StandardCharsets.UTF_8);
        }

        Rate rate = new Rate();
        rate.setType(type);
        rate.setRate(rateValue);
        rate.setDate(date);
        
        int id;
        try {
            id = rateRepository.add(rate);
        } catch (IllegalArgumentException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

        return "redirect:/success.html?id=" + URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
    }
}