package com.jjapartments.backend.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

import com.jjapartments.backend.models.Tenant;
import com.jjapartments.backend.repository.TenantRepository;
import com.jjapartments.backend.exception.ErrorException;

@Controller
public class AddTenantsController{
    @Autowired
    private TenantRepository tenantRepository;
    @GetMapping()
    public String AddTenant(
    @RequestParam("first_name") String first_name,
    @RequestParam("last_name") String last_name,
    @RequestParam("unit") String unit,
    @RequestParam("email") String email,
    @RequestParam("phone_number") String number) {

        Tenant tenant = new Tenant();

        tenant.setFirstName(first_name);
        tenant.setLastName(last_name);
        tenant.setEmail(email);
        tenant.setUnit(unit);
        tenant.setPhoneNumber(number);

        try {
            tenantRepository.add(tenant);

        } catch(ErrorException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        
        return "redirect:/success.html?first_name=" + URLEncoder.encode(first_name, StandardCharsets.UTF_8);
    }
}