package com.example.application.Backend.model;

public class PaymentMethod {
    private int id;
    private String betalningsmetod;


    public PaymentMethod(int id, String betalningsmetod) {
        this.id = id;
        this.betalningsmetod = betalningsmetod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBetalningsmetod() {
        return betalningsmetod;
    }

    public void setBetalningsmetod(String betalningsmetod) {
        this.betalningsmetod = betalningsmetod;
    }
}
