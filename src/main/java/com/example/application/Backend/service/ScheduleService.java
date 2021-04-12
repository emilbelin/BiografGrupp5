package com.example.application.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;




    public List<Object> findScheduleID()
    {
        String sql = "SELECT id FROM arbetsschema";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
}
