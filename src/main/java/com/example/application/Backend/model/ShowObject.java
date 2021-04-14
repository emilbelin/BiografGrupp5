package com.example.application.Backend.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowObject {

    private Movie movie;
    private LocalTime time;
    private LocalDate date;
    private Cinema cinema;
    private Lounge lounge;

    public ShowObject (Movie movie, LocalTime time, LocalDate date, Cinema cinema, Lounge lounge)
    {
        this.movie = movie;
        this.time = time;
        this.date = date;
        this.cinema = cinema;
        this.lounge = lounge;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Lounge getLounge() {
        return lounge;
    }

    public void setLounge(Lounge lounge) {
        this.lounge = lounge;
    }
}
