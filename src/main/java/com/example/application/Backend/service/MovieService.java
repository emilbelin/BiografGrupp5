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

    public void delete(String titel)
    {
        String sql = "DELETE FROM film WHERE titel = ?";
        jdbcTemplate.update(sql, titel);
    }

    public void addMovie(Movie movie)
    {
        String sql = "INSERT INTO film(titel, sprak, langd, aldersgrans, genre) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, movie.getTitel(), movie.getSprak(), movie.getLangd(), movie.getAldergrans(), movie.getGenre());
    }

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
