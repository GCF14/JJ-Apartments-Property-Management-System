package com.jjapartments.backend.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jjapartments.backend.models.Payment;
import com.jjapartments.backend.repository.PaymentRepository;

@Controller
public class AddPaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/addPayment")
    public String addPayment(
        @RequestParam("tenant_id") int tenantId,
        @RequestParam("reason") String reason,
        @RequestParam("mode_of_payment") String modeOfPayment,
        @RequestParam("amount") float amount,
        @RequestParam("due_date") String dueDate,
        @RequestParam("is_paid") boolean isPaid,
        @RequestParam(value = "month_of_start", required = false) String monthStart,
        @RequestParam(value = "month_of_end", required = false) String monthEnd,
        @RequestParam(value = "paid_at", required = false) String paidAt
    ) {

        List<String> validReasons = List.of("Monthly Due", "Miscellaneous", "Maintenance");
        List<String> validModes = List.of("Cash", "GCash", "Bank", "Others");

        if (!validReasons.contains(reason)) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid reason", StandardCharsets.UTF_8);
        }

        if (!validModes.contains(modeOfPayment)) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode("Invalid mode of payment", StandardCharsets.UTF_8);
        }

        Payment payment = new Payment();
        payment.setTenantId(tenantId);
        payment.setReason(reason);
        payment.setModeOfPayment(modeOfPayment);
        payment.setAmount(amount);
        payment.setDueDate(dueDate);
        payment.setIsPaid(isPaid);

        if (monthStart != null && !monthStart.isBlank())
            payment.setMonthOfStart(monthStart);

        if (monthEnd != null && !monthEnd.isBlank())
            payment.setMonthOfEnd(monthEnd);

        if (paidAt != null && !paidAt.isBlank())
            payment.setPaidAt(paidAt);

        int id;
        try {
            id = paymentRepository.add(payment);
        } catch (IllegalArgumentException e) {
            return "redirect:/error.html?errorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

        return "redirect:/success.html?tenant_id=" + URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8);
    }
}