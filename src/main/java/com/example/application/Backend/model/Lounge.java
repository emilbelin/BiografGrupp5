package com.example.application.Backend.model;

public class Lounge {
    private int id, platser, Biograf_id;

    public Lounge(int id, int platser, int Biograf_id)
    {
        this.id = id;
        this.platser = platser;
        this.Biograf_id = Biograf_id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatser() {
        return platser;
    }

    public void setPlatser(int platser) {
        this.platser = platser;
    }

    public int getBiograf_id() {
        return Biograf_id;
    }

    public void setBiograf_id(int biograf_id) {
        Biograf_id = biograf_id;
    }
}
