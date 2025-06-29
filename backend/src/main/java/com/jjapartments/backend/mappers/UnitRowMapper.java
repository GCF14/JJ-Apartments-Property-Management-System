package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.Unit;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitRowMapper implements RowMapper<Unit>{
    @Override
    public Unit mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Unit unit = new Unit();
        unit.setId(rs.getInt("id"));
        unit.setUnitNumber(rs.getString("unit_number"));
        unit.setIsOccupied(rs.getBoolean("is_occupied"));
        return unit;
    }
}
