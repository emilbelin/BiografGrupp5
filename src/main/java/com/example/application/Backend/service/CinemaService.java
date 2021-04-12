package com.example.application.Backend.service;

import com.example.application.Backend.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CinemaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List findAll()
    {
        try
        {
            return jdbcTemplate.query("SELECT namn, telefon, adress FROM biograf", (rs, rowNum) -> new Cinema(rs.getString("namn"), rs.getString("telefon"), rs.getString("adress")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

}
