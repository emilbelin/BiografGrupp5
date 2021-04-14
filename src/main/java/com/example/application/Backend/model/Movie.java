package com.example.application.Backend.model;

public class Movie {
    private int id;
    private String titel;
    private String sprak;
    private String aldergrans;
    private String genre;
    private int langd;

    public Movie(int id, String titel, String sprak, String aldergrans, String genre, int langd)
    {
        this.id = id;
        this.titel = titel;
        this.sprak = sprak;
        this.aldergrans = aldergrans;
        this.genre = genre;
        this.langd = langd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getSprak() {
        return sprak;
    }

    public void setSprak(String sprak) {
        this.sprak = sprak;
    }

    public String getAldergrans() {
        return aldergrans;
    }

    public void setAldergrans(String aldergrans) {
        this.aldergrans = aldergrans;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLangd() {
        return langd;
    }

    public void setLangd(int langd) {
        this.langd = langd;
    }
}
