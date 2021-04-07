package com.example.application.Backend;

import javax.persistence.*;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String titel;

    private String sprak;

    private Integer langd;

    private String aldersgrans;

    private String genre;

    //protected Film() {} //Används ej direkt, bara för JPA

    //ingen constructor för crudRepon
    /*public Film(String titel, String sprak, String langd, String aldersgrans, String genre) {

        this.titel = titel;
        this.sprak = sprak;
        this.langd = langd;
        this.aldersgrans = aldersgrans;
        this.genre = genre;
    }

     */

    public Integer getId() {
        return id;
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

    public Integer getLangd() {
        return langd;
    }

    public void setLangd(Integer langd) {
        this.langd = langd;
    }

    public String getAldersgrans() {
        return aldersgrans;
    }

    public void setAldersgrans(String aldersgrans) {
        this.aldersgrans = aldersgrans;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
