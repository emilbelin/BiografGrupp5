package com.example.application.Backend.service;

import com.example.application.Backend.model.Cinema;
import com.example.application.Backend.model.Lounge;
import com.example.application.Backend.model.ShowViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShowService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * @param id of the show to remove
     *           takes parameter to delete a selected show in the ShowView
     */
    public void deleteShow(int id)
    {
        String sql = "DELETE FROM forestallningar WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * @param movieId
     * @param loungeId
     * @param time
     * @param date
     * calls a stored procedure to add a new show to the DB using the parameters, which are taken from the ShowForm fields.
     */
    public void addToShow(int movieId, int loungeId, String time, LocalDate date)
    {
        String sql = "CALL ny_forestallning (?,?,?,?)";
        jdbcTemplate.update(sql, movieId, loungeId, time, date);

    }

    /**
     *
     * @param bio_id the cinema to take lounges from
     * @return a list of lounges for the selected cinema
     */
    public List findLoungeForCinema(int bio_id)
    {
        String sql = "SELECT * FROM salong WHERE Biograf_id = '" + bio_id + "' ";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Lounge(rs.getInt("id"),rs.getInt("salongNr"), rs.getInt("platser"), rs.getInt("Biograf_id")));
    }

    /**
     *
     * @return a list of cinema objects with values from DB
     */
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

    /**
     * @return a list of lounges with values from the DB
     */
    public List findLounges()
    {
        String sql = "SELECT * FROM salong";

        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Lounge(rs.getInt("id"),rs.getInt("salongNr"), rs.getInt("platser"), rs.getInt("Biograf_id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

    /**
     * @return a list of shows from the DB view "forestallning_view" to fill the ShowView grid with.
     */
    public List findForestallningView()
    {
        String sql = "SELECT * FROM forestallning_view";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new ShowViewModel(rs.getString("Film"), rs.getString("Biograf"), rs.getInt("Salong"), rs.getInt("Platser_kvar"), rs.getString("Tid"), rs.getString("Datum"), rs.getInt("id")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }






}
