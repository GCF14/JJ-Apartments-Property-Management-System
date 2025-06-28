package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.Expense;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRowMapper implements RowMapper<Expense>{
    @Override
    public Expense mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getInt("id"));
        expense.setAmount(rs.getFloat("amount"));
        expense.setReason(rs.getString("reason"));
        expense.setDate(rs.getString("date"));
        return expense;
    }
}
