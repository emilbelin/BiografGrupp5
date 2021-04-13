package com.example.application.Backend.model;


import com.example.application.Backend.service.ScheduleService;

import java.util.List;

public enum Skift {
    DAG("11.00 - 19.00"),
    KVÄLL("18.00 - 02.00");

    private String value;

    Skift(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
