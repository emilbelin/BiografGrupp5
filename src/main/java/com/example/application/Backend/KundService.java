package com.example.application.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KundService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List findAll()
    {
        try
        {
            return jdbcTemplate.query("SELECT fornamn, efternamn, telefonnummer FROM kund", (rs, rowNum) -> new Kund(rs.getString("fornamn"),
                    rs.getString("efternamn"), rs.getString("telefonnummer")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
}
