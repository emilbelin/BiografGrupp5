package com.example.application.Backend.model;

public enum Station {
    CLEANING("Städ"),
    CASHREGISTER("Kassa"),
    TICKET("Biljettkontroll"),
    MOVIECHANGE("Filmbyte"),
    MISC("Övrigt");

    private String value;

    Station(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
