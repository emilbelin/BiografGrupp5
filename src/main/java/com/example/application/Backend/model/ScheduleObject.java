package com.example.application.Backend.model;

import java.time.LocalDate;

public class ScheduleObject {

    private Staff staff;
    private LocalDate date;
    private Skift skift;
    private Station station;
    public ScheduleObject(Staff staff, LocalDate date, Skift skift, Station station)
    {
        this.staff = staff;
        this.date = date;
        this.skift = skift;
        this.station = station;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Skift getSkift() {
        return skift;
    }

    public void setSkift(Skift skift) {
        this.skift = skift;
    }
}
