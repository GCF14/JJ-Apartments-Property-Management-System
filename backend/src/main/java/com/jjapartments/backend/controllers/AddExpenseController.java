package com.jjapartments.backend.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

import com.jjapartments.backend.models.Expense;
//import com.jjapartments.backend.exception.ErrorException; unused
import com.jjapartments.backend.repository.ExpenseRepository;


@Controller
public class AddExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;
    @GetMapping()
    public String addExpense(
    @RequestParam("amount") float amount,
    @RequestParam("reason") String reason,
    @RequestParam("date") String date) {

    List<String> validReasons = List.of("Maintenance", "Utilities", "Supplies", "Repair", "Other");

    if (!validReasons.contains(reason)) {
        return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid reason type", StandardCharsets.UTF_8);
    }

    Expense expense = new Expense();
    expense.setAmount(amount);
    expense.setReason(reason);
    expense.setDate(date);

    int id;
    try {
        id = expenseRepository.add(expense);
    } catch (IllegalArgumentException e) {
        return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
    }
    
    return "redirect:/success.html?id=" + URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
}
}