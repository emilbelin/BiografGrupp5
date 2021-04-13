package com.example.application.Backend.model;

public class StaffSchedule {

    private String fornamn, efternamn, station, datum, skiftstart, skiftslut;

    public StaffSchedule(String fornamn, String efternamn, String station, String datum, String skiftstart, String skiftslut) {
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.station = station;
        this.datum = datum;
        this.skiftstart = skiftstart;
        this.skiftslut = skiftslut;
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getSkiftslut() {
        return skiftslut;
    }

    public String getSkiftstart() {
        return skiftstart;
    }

    public void setSkiftslut(String skiftslut) {
        this.skiftslut = skiftslut;
    }

    public void setSkiftstart(String skiftstart) {
        this.skiftstart = skiftstart;
    }
}