package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.Expense;
import com.jjapartments.backend.mappers.ExpenseRowMapper;

@Repository
public class ExpenseRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Expense> findAll() {
        String sql = "SELECT * FROM expenses"; 
        return jdbcTemplate.query(sql, new ExpenseRowMapper());
    }

    public int add(Expense expense) {
        String sql = "INSERT INTO expenses(amount, reason, date) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, expense.getAmount(), expense.getReason(), expense.getDate());
       
    }

    public int delete(Expense expense) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        return jdbcTemplate.update(sql, expense.getId());
    }
}