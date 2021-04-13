package com.example.application.Backend.service;

import com.example.application.Backend.model.StaffSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffScheduleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List findScheduleList()
    {
        String sql = "SELECT * FROM personal_schema";

        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new StaffSchedule(rs.getString("fornamn"), rs.getString("efternamn"), rs.getString("namn"),
                    rs.getString("datum"), rs.getString("skiftstart"), rs.getString("skiftslut")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

}
