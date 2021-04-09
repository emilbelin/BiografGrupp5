package com.example.application.Backend.Film;

import com.example.application.Backend.Film.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete(String titel)
    {
        String sql = "DELETE FROM film WHERE titel = ?";
        jdbcTemplate.update(sql, titel);
    }
    public void addFilm(Film film)
    {
        String sql = "INSERT INTO film(titel, sprak, langd, aldersgrans, genre) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[] { film.getTitel(), film.getSprak(), film.getLangd(), film.getAldergrans(), film.getGenre()});
    }
    public List findAll()
    {
        try
        {
            return jdbcTemplate.query("SELECT titel, sprak, langd, aldersgrans, genre FROM film", (rs, rowNum) -> new Film(rs.getString("titel"), rs.getString("sprak"), rs.getString("aldersgrans"), rs.getString("genre"), rs.getInt("langd")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }

}
