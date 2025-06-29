package com.jjapartments.backend.mappers;
import org.springframework.jdbc.core.RowMapper;

import com.jjapartments.backend.models.Payment;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment>{
    @Override
    public Payment mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setTenantId(rs.getInt("tenants_id"));
        payment.setModeOfPayment(rs.getString("mode_of_payment"));
        payment.setAmount(rs.getFloat("amount"));
        payment.setDueDate(rs.getString("due_date"));
        payment.setMonthOfStart(rs.getString("month_of_start"));
        payment.setMonthOfEnd(rs.getString("month_of_end"));
        payment.setIsPaid(rs.getBoolean("is_paid"));
        payment.setPaidAt(rs.getString("paid_at"));
        
        return payment;
    }
}
