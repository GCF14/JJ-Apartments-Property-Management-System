package com.jjapartments.backend.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import com.jjapartments.backend.models.Unit;
import com.jjapartments.backend.repository.UnitRepository;
import com.jjapartments.backend.exception.ErrorException;

@Controller
public class AddUnitController {

    @Autowired
    private UnitRepository unitRepository;
    @GetMapping()
    public String AddUnit(
    @RequestParam("id") int unitid,  
    @RequestParam("unit_number") String unitnumber,
    @RequestParam("is_occuped") boolean isoccupied){

        Unit unit = new Unit();

        unit.setId(unitid);
        unit.setUnitNumber(unitnumber);
        unit.setIsOccupied(isoccupied);


        try {
            unitRepository.add(unit);

        } catch(ErrorException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        
        return "redirect:/success.html?firstname=";   
    }

}
