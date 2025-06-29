package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.Payment;
import com.jjapartments.backend.mappers.PaymentRowMapper;

@Repository
public class PaymentRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Payment> findAll() {
        String sql = "SELECT * FROM payments"; 
        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    public int add(Payment payment) {
        String sql = "INSERT INTO payments(tenant_id, reason, mode_of_payment, amount, due_date, month_of_start, month_of_end, is_paid, paid_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, payment.getTenantId(), payment.getReason(), payment.getModeOfPayment(), payment.getAmount(), payment.getDueDate(), payment.getMonthOfStart(), payment.getMonthOfEnd(), payment.getIsPaid(), payment.getPaidAt());
       
    }

    public int delete(Payment payment) {
        String sql = "DELETE FROM payments WHERE id = ?";
        return jdbcTemplate.update(sql, payment.getId());
    }
}