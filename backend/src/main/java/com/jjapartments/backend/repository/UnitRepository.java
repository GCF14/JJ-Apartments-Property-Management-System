package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.jjapartments.backend.models.Unit;
import com.jjapartments.backend.mappers.UnitRowMapper;

@Repository
public class UnitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Unit> findAll() {
        String sql = "SELECT * FROM units";
        return jdbcTemplate.query(sql, new UnitRowMapper());
    }

    public boolean unitNumberExists(Unit unit) {
        String sqlChecker = "SELECT COUNT(*) FROM units WHERE unit_number = ?";
        Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, unit.getUnitNumber());
        return count != null && count > 0;
    }

    public int add(Unit unit) {
        String sqlChecker = "SELECT COUNT(*) FROM units WHERE unit_number = ?";
        Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, unit.getUnitNumber());

        if (count != null && count == 0) {
            String sql = "INSERT INTO units(unit_number, is_occupied) VALUES (?, ?)";
            return jdbcTemplate.update(sql, unit.getUnitNumber(), unit.isOccupied());
        } else {
            throw new IllegalArgumentException("The unit number already exists.");
        }
    }

    public int delete(Unit unit) {
        String sql = "DELETE FROM units WHERE id = ?";
        return jdbcTemplate.update(sql, unit.getId());
    }
}
