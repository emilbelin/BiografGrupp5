package com.example.application.Backend.service;

import com.example.application.Backend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List findAll()
    {
        try
        {
            return jdbcTemplate.query("SELECT fornamn, efternamn, telefonnummer FROM kund", (rs, rowNum) -> new Customer(rs.getString("fornamn"),
                    rs.getString("efternamn"), rs.getString("telefonnummer")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
}
