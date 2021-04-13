package com.example.application.Backend.service;

import com.example.application.Backend.model.StationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String findSkift(int index)
    {
        String returnString = "";
        String sql = "SELECT start, slut FROM skift";
        List<String> data = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1) + resultSet.getString(2);
            }
        });
        try {
            switch (index) {
                case (0):
                    returnString = data.get(0);
                case (1):
                    returnString = data.get(1);
            }
        }
        catch(Exception e)
        {
            return "";
        }
        return returnString;
    }
    public List<Object> findStation()
    {
        String sql = "SELECT id, namn FROM station";
        try{
            return jdbcTemplate.query(sql, (rs, rowNum) -> new StationObject(rs.getInt("id"), rs.getString("namn")));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
    public List<Object> findScheduleID()
    {
        String sql = "SELECT id FROM arbetsschema";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
        }
        catch(Exception e)
        {
            return new ArrayList();
        }
    }
}
