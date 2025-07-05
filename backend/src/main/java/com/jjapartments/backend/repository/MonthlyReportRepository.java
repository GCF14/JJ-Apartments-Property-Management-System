package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.MonthlyReport;
import com.jjapartments.backend.exception.ErrorException;
import com.jjapartments.backend.mappers.MonthlyReportRowMapper;

@Repository
public class MonthlyReportRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<MonthlyReport> findAll() {
        String sql = "SELECT * FROM monthly_reports"; 
        return jdbcTemplate.query(sql, new MonthlyReportRowMapper());
    }

    // public int add(MonthlyReport monthlyReport) {
    //     String sql = "INSERT INTO monthly_reports(tenant_id, reason, mode_of_monthlyReport, amount, due_date, month_of_start, month_of_end, is_paid, paid_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //     return jdbcTemplate.update(sql, monthlyReport.getTenantId(), monthlyReport.getReason(), monthlyReport.getModeOfMonthlyReport(), monthlyReport.getAmount(), monthlyReport.getDueDate(), monthlyReport.getMonthOfStart(), monthlyReport.getMonthOfEnd(), monthlyReport.getIsPaid(), monthlyReport.getPaidAt());
       
    // }

    public int delete(int id) {
        String sql = "DELETE FROM monthly_reports WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int add(MonthlyReport report) {
        String sql = "INSERT INTO monthly_reports(year, month, total_earnings, total_expenses, net_income) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                report.getYear(),
                report.getMonth(),
                report.getTotalEarnings(),
                report.getTotalExpenses(),
                report.getNetIncome());
    }

    public float sumPayments(int year, int month) {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM payments " +
                     "WHERE is_paid = 1 AND YEAR(paid_at) = ? AND MONTH(paid_at) = ?";
        return jdbcTemplate.queryForObject(sql, Float.class, year, month);
    }

    public float sumExpenses(int year, int month) {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM expenses " +
                     "WHERE YEAR(date) = ? AND MONTH(date) = ?";
        return jdbcTemplate.queryForObject(sql, Float.class, year, month);
    }

    public MonthlyReport findById(int id) {
        String sql = "SELECT * FROM monthly_reports WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new MonthlyReportRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ErrorException("Monthly Report with id " + id + " not found.");
        }
    }
    
}