package com.example.application.Backend.model;

public class Staff {

    private int id;
    private String fornamn;
    private String efternamn;
    private String titel;

    public Staff(int id, String fornamn, String efternamn, String titel)
    {
        this.id = id;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.titel = titel;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString()
    {
        String name;
        name = this.fornamn + " " + this.efternamn;
        return name;
    }

    public String getFullName()
    {
        return fornamn + " " + efternamn;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
