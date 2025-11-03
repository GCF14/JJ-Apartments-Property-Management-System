package com.jjapartments.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DuplicateKeyException;

import com.jjapartments.backend.models.MonthlyReport;
import com.jjapartments.backend.repository.MonthlyReportRepository;
import com.jjapartments.backend.repository.PaymentRepository;
import com.jjapartments.backend.repository.UnitRepository;
import com.jjapartments.backend.repository.UtilityRepository;
import com.jjapartments.backend.repository.ExpenseRepository;
import com.jjapartments.backend.exception.ErrorException;
import com.jjapartments.backend.models.Unit;

@RestController
@RequestMapping("/api/monthlyreports")
public class MonthlyReportController {

    @Autowired
    private MonthlyReportRepository monthlyReportRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UtilityRepository utilityRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addMonthlyReport(
            @RequestParam("year") int year,
            @RequestParam("month") int month) {
        try {
            // float totalEarnings = monthlyReportRepository.sumPayments(year, month);
            // float totalExpenses = monthlyReportRepository.sumExpenses(year, month);
            // float netIncome = totalEarnings - totalExpenses;
            List<Unit> units = unitRepository.findAll();
            for (int i = 0; i < units.size(); i++) {
                int unitId = units.get(i).getId();
                float monthlyDues = paymentRepository.getMonthlyAmountByUnitId(unitId, year, month);
                float utilityBills = utilityRepository.getMonthlyAmountByUnitId(unitId, year, month);
                float expenses = expenseRepository.getMonthlyAmountById(unitId, year, month);
                MonthlyReport report = new MonthlyReport();
                report.setYear(year);
                report.setMonth(month);
                report.setUnitId(unitId);
                report.setMonthlyDues(monthlyDues);
                report.setUtilityBills(utilityBills);
                report.setExpenses(expenses);
                monthlyReportRepository.add(report);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Monthly report successfully created for " + month + "/" + year);
        } catch (DuplicateKeyException e) { // if report already exists with same month and year
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Monthly report already exists for " + month + "/" + year);
        } catch (ErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Could not generate monthly report: " + e.getMessage());
        }
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<MonthlyReport>> getAllReports() {
        List<MonthlyReport> reports = monthlyReportRepository.findAll();
        return ResponseEntity.ok(reports);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMonthlyReport(@RequestParam("year") int year,
            @RequestParam("month") int month) {
        int rowsAffected = monthlyReportRepository.delete(year, month);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Monthly Report deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monthly Report not found");
        }
    }

}