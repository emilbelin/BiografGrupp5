package com.example.application.Backend.model;

public class Customer {

    private String fornamn, efternamn, telefonnummer;
    public Customer(String fornamn, String efternamn, String telefonnummer)
    {
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.telefonnummer = telefonnummer;
    }

    public String getFornamn() {
        return fornamn;
    }

    public void setFornamn(String fornamn) {
        this.fornamn = fornamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
