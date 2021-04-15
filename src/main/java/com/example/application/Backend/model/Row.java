package com.example.application.Backend.model;

public class Row {
    private int id, nummer, Salong_id;

    public Row(int id, int nummer, int salong_id) {
        this.id = id;
        this.nummer = nummer;
        Salong_id = salong_id;
    }

    public String convertNummer()
    {
        return String.valueOf(nummer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getSalong_id() {
        return Salong_id;
    }

    public void setSalong_id(int salong_id) {
        Salong_id = salong_id;
    }
}
