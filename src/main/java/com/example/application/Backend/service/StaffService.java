package com.example.application.Backend.service;

import com.example.application.Backend.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffService {
    @Autowired
    JdbcTemplate jdbcTemplate;




    public List<Staff> findAll()
    {
        String sql = "SELECT id, fornamn, efternamn, titel FROM personal";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Staff(rs.getInt("id"), rs.getString("fornamn"), rs.getString("efternamn"), rs.getString("titel")));
        }
        catch(Exception e)
        {
           return new ArrayList();
        }
    }

}
