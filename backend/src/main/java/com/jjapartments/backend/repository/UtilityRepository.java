package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.Utility;
import com.jjapartments.backend.mappers.UtilityRowMapper;

@Repository
public class UtilityRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Utility> findAll() {
        String sql = "SELECT * FROM utilities"; 
        return jdbcTemplate.query(sql, new UtilityRowMapper());
    }

    public int add(Utility utility) {
        String sql = "INSERT INTO utilities(type, previous_reading, current_reading, total_meter, total_amount, due_date, month_of_start, month_of_end, is_paid, paid_at, tenants_id, rates_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, utility.getType(), utility.getPreviousReading(), utility.getCurrentReading(), utility.getTotalMeter(), utility.getTotalAmount(), utility.getDueDate(), utility.getMonthOfStart(), utility.getMonthOfEnd(), utility.getIsPaid(), utility.getPaidAt(), utility.getTenantId(), utility.getRateId());
       
    }

    public int delete(Utility utility) {
        String sql = "DELETE FROM utilities WHERE id = ?";
        return jdbcTemplate.update(sql, utility.getId());
    }
}