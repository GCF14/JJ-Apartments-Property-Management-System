package com.jjapartments.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.jjapartments.backend.models.User;
import com.jjapartments.backend.mappers.UserRowMapper;

@Repository
public class UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        String sql = "SELECT * FROM users"; 
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public boolean userExists(User user) {
    String sqlChecker = "SELECT COUNT(*) FROM users WHERE username = ?";
    Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, user.getUsername());
    return count != null && count > 0;
    }   

    // no need for this since id is auto increment (?)
    // public boolean idExists(User user) {
    // String sqlChecker = "SELECT COUNT(*) FROM users WHERE id = ?";
    // Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, user.getId());
    // return count != null && count > 0;
    // } 

    public boolean emailExists(User user) {
    String sqlChecker = "SELECT COUNT(*) FROM users WHERE email = ?";
    Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, user.getId());
    return count != null && count > 0;
    } 

    public int add(User user) {
        String sqlChecker = "SELECT COUNT(*) FROM users WHERE username = ? OR email = ?";
        Integer count = jdbcTemplate.queryForObject(sqlChecker, Integer.class, user.getId(), user.getUsername(), user.getEmail());

        if(count != null && count == 0){
            String sql = "INSERT INTO users(username, password, full_name, email, is_owner) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(), user.getIsOwner());
        } else if(userExists(user)){
            throw new IllegalArgumentException("The username is already taken.");
        } else if(emailExists(user)){
            throw new IllegalArgumentException("The email is already taken.");
        } else {
            throw new IllegalArgumentException("The user is already registered.");
        }
    }

    public int delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, user.getId());
    }
}