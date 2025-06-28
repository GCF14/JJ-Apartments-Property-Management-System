package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.MonthlyReport;
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

    public int delete(MonthlyReport monthlyReport) {
        String sql = "DELETE FROM monthly_reports WHERE id = ?";
        return jdbcTemplate.update(sql, monthlyReport.getId());
    }
}