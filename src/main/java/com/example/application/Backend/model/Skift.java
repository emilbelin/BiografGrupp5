package com.example.application.Backend.model;


public class Skift
{
    private int skift_id;
    private String start;
    private String slut;

    public Skift(int skift_id, String start, String slut) {
        this.skift_id = skift_id;
        this.start = start;
        this.slut = slut;
    }

    public String getValue()
    {
        String shiftTotal = "";
        shiftTotal = start + " - " + slut;
        return shiftTotal;
    }

    public int getSkift_id() {
        return skift_id;
    }

    public void setSkift_id(int skift_id) {
        this.skift_id = skift_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSlut() {
        return slut;
    }

    public void setSlut(String slut) {
        this.slut = slut;
    }
}
