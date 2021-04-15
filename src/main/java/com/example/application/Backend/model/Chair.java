package com.example.application.Backend.model;

public class Chair {
    private int id;
    private int nummer;
    private int Rad_id;


    public Chair(int id, int nummer, int rad_id) {
        this.id = id;
        this.nummer = nummer;
        this.Rad_id = rad_id;
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

    public int getRad_id() {
        return Rad_id;
    }

    public void setRad_id(int rad_id) {
        Rad_id = rad_id;
    }
}
