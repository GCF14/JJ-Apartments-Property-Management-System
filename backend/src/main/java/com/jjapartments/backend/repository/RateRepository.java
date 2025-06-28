package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.Rate;
import com.jjapartments.backend.mappers.RateRowMapper;

@Repository
public class RateRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Rate> findAll() {
        String sql = "SELECT * FROM rates"; 
        return jdbcTemplate.query(sql, new RateRowMapper());
    }

    public int add(Rate rate) {
        String sql = "INSERT INTO rates(type, rate) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, rate.getType(), rate.getRate(), rate.getDate());
    }

    public int delete(Rate rate) {
        String sql = "DELETE FROM rates WHERE id = ?";
        return jdbcTemplate.update(sql, rate.getId());
    }
}