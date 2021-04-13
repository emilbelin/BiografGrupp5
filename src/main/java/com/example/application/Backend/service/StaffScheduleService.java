package com.example.application.Backend.service;

import com.example.application.Backend.model.Skift;
import com.example.application.Backend.model.StaffSchedule;
import com.example.application.Backend.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class StaffScheduleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List findStation()
    {
        String sql = "SELECT * FROM station";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Station(rs.getInt("id"), rs.getString("namn")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    public void deleteSchedule(int id)
    {
        String sql = "DELETE FROM skift_has_personal WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public void addToScheme(LocalDate localDate, int personal_id, int stations_id, int skift_id)
    {
        String sql = "CALL nytt_schema (?,?,?,?)";
        jdbcTemplate.update(sql, personal_id, stations_id, localDate, skift_id);
    }

    public List findSkift()
    {
        String sql = "SELECT * FROM skift";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Skift(rs.getInt("id"),rs.getString("start"), rs.getString("slut")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    public List findScheduleList()
    {
        String sql = "SELECT * FROM personal_schema";

        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new StaffSchedule(rs.getString("fornamn"), rs.getString("efternamn"), rs.getString("namn"),
                    rs.getString("datum"), rs.getString("skiftstart"), rs.getString("skiftslut"), rs.getInt("schema_id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

}
