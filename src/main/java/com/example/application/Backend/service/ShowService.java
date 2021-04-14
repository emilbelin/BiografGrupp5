package com.example.application.Backend.service;

import com.example.application.Backend.model.Cinema;
import com.example.application.Backend.model.Lounge;
import com.example.application.Backend.model.ShowViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowService {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List findCinemas()
    {
        String sql = "SELECT * FROM biograf";

        try
        {
            return jdbcTemplate.query(sql, (rs, rownum) -> new Cinema(rs.getInt("id"), rs.getString("namn"), rs.getString("telefon"), rs.getString("adress")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    public List findLounges()
    {
        String sql = "SELECT * FROM salong";

        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Lounge(rs.getInt("id"), rs.getInt("platser"), rs.getInt("Biograf_id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    public List findForestallningView()
    {
        String sql = "SELECT * FROM forestallning_view";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new ShowViewModel(rs.getString("Film"), rs.getString("Biograf"), rs.getInt("Salong"), rs.getInt("Platser_kvar"), rs.getString("Tid"), rs.getString("Datum")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }






}
