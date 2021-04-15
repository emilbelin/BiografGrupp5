package com.example.application.Backend.service;

import com.example.application.Backend.model.Chair;
import com.example.application.Backend.model.Customer;
import com.example.application.Backend.model.PaymentMethod;
import com.example.application.Backend.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    CallableStatement callableStatement;
    public int findWithPhoneNumber(String phonenumber)
    {
        String sql = "SELECT id FROM kund WHERE telefonnummer = ?";
        int returnid = 0;
        try {
            returnid = jdbcTemplate.queryForObject(sql, new Object[]{phonenumber}, Integer.class);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }

            return returnid;
    }



    public void addBooking(int kund_id, int betalnings_id, int show_id, int stol_id)
    {
        String sql = "CALL ny_bokning (?,?,?,?)";
        jdbcTemplate.update(sql, kund_id, betalnings_id, show_id, stol_id);
    }

    public void addCustomer(String firstname, String lastname, String telefon)
    {
        String sql = "CALL ny_kund (?,?,?)";
        jdbcTemplate.update(sql, firstname, lastname, telefon);
    }

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

    public List<Customer> findCustomers()
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
