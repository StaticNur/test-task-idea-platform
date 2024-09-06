package com.programming.techie.entity;

import java.util.Date;

public class Flight {
    private String carrier;
    private Date departure;
    private Date arrival;
    private int price;

    public Flight(String carrier, Date departure, Date arrival, int price) {
        this.carrier = carrier;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public String getCarrier() {
        return carrier;
    }

    public long getFlightDuration() {
        return (arrival.getTime() - departure.getTime()) / (60 * 1000);  // in minutes
    }

    public int getPrice() {
        return price;
    }
}
