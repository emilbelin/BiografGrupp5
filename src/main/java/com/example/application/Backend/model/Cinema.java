package com.example.application.Backend.model;

public class Cinema {
    private String namn;
    private String telefon;
    private String adress;
    public Cinema(String namn, String telefon, String adress)
    {

        this.namn = namn;
        this.telefon = telefon;
        this.adress = adress;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
