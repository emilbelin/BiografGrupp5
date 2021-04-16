package com.example.application.Backend.service;

import com.example.application.Backend.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieService  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param titel is the titel of the movie we want to delete
     *              ´deletes the selected movie in the MovieForm from the DB
     */
    public void delete(String titel)
    {
        String sql = "DELETE FROM film WHERE titel = ?";
        jdbcTemplate.update(sql, titel);
    }

    /**
     * @param movie takes in the movieobject with the values from the form fiels in MovieForm.
     *              adds a new movie using the parameters to the DB
     */
    public void addMovie(Movie movie)
    {
        String sql = "INSERT INTO film(titel, sprak, langd, aldersgrans, genre) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, movie.getTitel(), movie.getSprak(), movie.getLangd(), movie.getAldergrans(), movie.getGenre());
    }

    /**
     * @param movie takes in the movieobject with the values from the form fields in MovieForm
     *              updates the selected movie with the new values in the DB
     */
    public void saveMovie(Movie movie)
    {
        jdbcTemplate.update("UPDATE film SET titel = ?, sprak = ?,langd = ?, aldersgrans = ?, genre = ? WHERE titel = ?",
                movie.getTitel(),
                movie.getSprak(),
                movie.getLangd(),
                movie.getAldergrans(),
                movie.getGenre(),
                movie.getTitel());

    }

    /**
     * @return a list of new movie objects with values from the db
     */
    public List findAll()
    {
        try
        {
            return jdbcTemplate.query("SELECT id, titel, sprak, langd, aldersgrans, genre FROM film", (rs, rowNum) -> new Movie(rs.getInt("id"), rs.getString("titel"), rs.getString("sprak"), rs.getString("aldersgrans"), rs.getString("genre"), rs.getInt("langd")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

}
