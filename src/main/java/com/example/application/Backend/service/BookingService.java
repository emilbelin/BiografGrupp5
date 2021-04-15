package com.example.application.Backend.service;

import com.example.application.Backend.model.Chair;
import com.example.application.Backend.model.PaymentMethod;
import com.example.application.Backend.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List findRow(int salong_id)
    {
        String sql = "SELECT * FROM rad WHERE Salong_id = " + salong_id + "";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Row(rs.getInt("id"), rs.getInt("nummer"), rs.getInt("Salong_id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    public List findChair(int rad_id)
    {
        String sql = "SELECT * FROM stol WHERE Rad_id = " + rad_id + "";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Chair(rs.getInt("id"), rs.getInt("nummer"), rs.getInt("Rad_id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
    public List findPaymentMethod()
    {
        String sql = "SELECT * FROM betalningssatt";

        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new PaymentMethod(rs.getInt("id"), rs.getString("betalningsmetod")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }




}
