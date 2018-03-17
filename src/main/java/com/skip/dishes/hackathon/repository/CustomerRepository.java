package com.skip.dishes.hackathon.repository;

import com.skip.dishes.hackathon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public static final RowMapper<Customer> ROW_MAPPER = new RowMapper<Customer>() {
        @Nullable
        @Override
        public Customer mapRow(ResultSet rs, int i) throws SQLException {

            Customer customer = new Customer();
            customer.setId(rs.getLong("customer.id"));
            customer.setName(rs.getString("customer.name"));
            customer.setEmail(rs.getString("customer.email"));
            customer.setAddress(rs.getString("customer.address"));
            customer.setPassword(rs.getString("customer.password"));
            customer.setCreation(
                    rs.getTimestamp("customer.creation").toLocalDateTime());


            return customer;
        }
    };

    public Customer findOneByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM customer AS customer WHERE customer.email=?",
                    new Object[]{username}, ROW_MAPPER);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Customer save(Customer customer) {
        try {
            customer.setCreation(LocalDateTime.now());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", customer.getName());
            parameters.put("email", customer.getEmail());
            parameters.put("address", customer.getAddress());
            parameters.put("password", customer.getPassword());
            parameters.put("creation", customer.getCreation());
            Long id = (Long) new SimpleJdbcInsert(jdbcTemplate).withTableName("customer").usingGeneratedKeyColumns("id").executeAndReturnKey(parameters);

            customer.setId(id);

            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
