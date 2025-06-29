package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.MonthlyReport;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyReportRowMapper implements RowMapper<MonthlyReport>{
    @Override
    public MonthlyReport mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.setId(rs.getInt("id"));
        monthlyReport.setYear(rs.getInt("year"));
        monthlyReport.setMonth(rs.getInt("month"));
        monthlyReport.setTotalEarnings(rs.getFloat("total_earnings"));
        monthlyReport.setTotalExpenses(rs.getFloat("total_expenses"));
        monthlyReport.setNetIncome(rs.getFloat("net_income"));
        return monthlyReport;
    }
}
