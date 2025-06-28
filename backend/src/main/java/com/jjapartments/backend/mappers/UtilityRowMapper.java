package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.Utility;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilityRowMapper implements RowMapper<Utility>{
    @Override
    public Utility mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Utility utility = new Utility();
        utility.setId(rs.getInt("id"));
        utility.setPreviousReading(rs.getFloat("previous_reading"));
        utility.setCurrentReading(rs.getFloat("current_reading"));
        utility.setTotalMeter(rs.getFloat("total_meter"));
        utility.setTotalAmount(rs.getFloat("total_amount"));
        utility.setDueDate(rs.getString("due_date"));
        utility.setMonthOfStart(rs.getString("month_of_start"));
        utility.setMonthOfEnd(rs.getString("month_of_end"));
        utility.setIsPaid(rs.getBoolean("is_paid"));
        utility.setPaidAt(rs.getString("paid_at"));
        utility.setTenantId(rs.getInt("tenants_id"));
        utility.setRateId(rs.getInt("rates_id"));
        return utility;
    }
}
