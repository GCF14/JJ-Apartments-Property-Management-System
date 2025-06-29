package com.jjapartments.backend.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

import com.jjapartments.backend.models.MonthlyReport;
import org.springframework.web.bind.annotation.PostMapping;
import com.jjapartments.backend.repository.MonthlyReportRepository;

@Controller
public class AddMonthlyReportController {

    @Autowired
    private MonthlyReportRepository monthlyReportRepository;

    @PostMapping("/addMonthlyReport")
    public String addMonthlyReport(
        @RequestParam("year") int year,
        @RequestParam("month") int month
    ) {
        try {
            float totalEarnings = monthlyReportRepository.sumPayments(year, month);
            float totalExpenses = monthlyReportRepository.sumExpenses(year, month);
            float netIncome = totalEarnings - totalExpenses;

            MonthlyReport report = new MonthlyReport();
            report.setYear(year);
            report.setMonth(month);
            report.setTotalEarnings(totalEarnings);
            report.setTotalExpenses(totalExpenses);
            report.setNetIncome(netIncome);

            monthlyReportRepository.add(report);

            return "redirect:/success.html?month=" + month + "&year=" + year;

        } catch (Exception e) {
            return "redirect:/error.html?errorMessage=" +
                    URLEncoder.encode("Could not generate monthly report: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }
}