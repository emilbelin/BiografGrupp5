package com.example.application.Backend.model;

public class ScheduleViewModel {

    private String fornamn, efternamn, station, datum, skiftstart, skiftslut;
    private int schema_id;
    public ScheduleViewModel(String fornamn, String efternamn, String station, String datum, String skiftstart, String skiftslut, int schema_id) {
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.station = station;
        this.datum = datum;
        this.skiftstart = skiftstart;
        this.skiftslut = skiftslut;
        this.schema_id = schema_id;
    }

    public int getSchema_id() {
        return schema_id;
    }

    public void setSchema_id(int schema_id) {
        this.schema_id = schema_id;
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