package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.Rate;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateRowMapper implements RowMapper<Rate>{
    @Override
    public Rate mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Rate rate = new Rate();
        rate.setId(rs.getInt("id"));
        rate.setType(rs.getString("type"));
        rate.setRate(rs.getFloat("rate"));
        rate.setDate(rs.getString("date"));
        return rate;
    }
}
