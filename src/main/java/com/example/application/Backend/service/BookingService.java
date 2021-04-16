package com.example.application.Backend.service;

import com.example.application.Backend.model.Chair;
import com.example.application.Backend.model.Customer;
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

    /**
     * @param phonenumber is the phonenumber to look for in the kund table
     * @returns the customer ID that matches the phonenumber
     */
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


    /**
     * @param kund_id
     * @param betalnings_id
     * @param show_id
     * @param stol_id
     * Adds a new ticket based on the input parameters,
     * in this case, based on the chosen show and (either new or existing) customer
     */
    public void addBooking(int kund_id, int betalnings_id, int show_id, int stol_id)
    {
        String sql = "CALL ny_bokning (?,?,?,?)";
        jdbcTemplate.update(sql, kund_id, betalnings_id, show_id, stol_id);
    }

    /**
     * @param firstname
     * @param lastname
     * @param telefon
     * Adds a new customer when a booking is created with a non-existing phonenumber.
     */
    public void addCustomer(String firstname, String lastname, String telefon)
    {
        String sql = "CALL ny_kund (?,?,?)";
        jdbcTemplate.update(sql, firstname, lastname, telefon);
    }


    /**
     * @param salong_id the lounge id
     * @return used the lounge id to find that lounge's rows.
     */
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

    /**
     * @return list of customer objects
     */
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

    /**
     * @param rad_id the row to look for the chair
     * @return a list of chair objects that matches the row
     */
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

    /**
     * @return a list of payment option objects from the database.
     */
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
