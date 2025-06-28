package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.Tenant;
import com.jjapartments.backend.mappers.TenantRowMapper;

@Repository
public class TenantRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Tenant> findAll() {
        String sql = "SELECT * FROM tenants"; 
        return jdbcTemplate.query(sql, new TenantRowMapper());
    }

    public boolean emailExists(Tenant tenant) {
    String sqlChecker = "SELECT COUNT(*) FROM tenants WHERE email = ?";
    Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, tenant.getId());
    return count != null && count > 0;
    } 

    public boolean phoneNumberExists(Tenant tenant) {
    String sqlChecker = "SELECT COUNT(*) FROM tenants WHERE phone_number = ?";
    Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, tenant.getPhoneNumber());
    return count != null && count > 0;
    } 

    public int add(Tenant tenant) {
        String sqlChecker = "SELECT COUNT(*) FROM tenants WHERE email = ? OR phone_numer = ?";
        Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, tenant.getEmail(), tenant.getPhoneNumber());

        if(count != null && count == 0){
            String sql = "INSERT INTO tenants(last_name, first_name, middle_initial, unit, email, phone_number) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, tenant.getLastName(), tenant.getFirstName(), tenant.getMiddleInitial(), tenant.getUnit(), tenant.getEmail(), tenant.getPhoneNumber());
        } else if(emailExists(tenant)){
            throw new IllegalArgumentException("The email is already taken.");
        } else if(phoneNumberExists(tenant)){
            throw new IllegalArgumentException("The phone number is already taken.");
        } else {
            throw new IllegalArgumentException("The tenant is already registered.");
        }
    }

    public int delete(Tenant tenant) {
        String sql = "DELETE FROM tenants WHERE id = ?";
        return jdbcTemplate.update(sql, tenant.getId());
    }
}