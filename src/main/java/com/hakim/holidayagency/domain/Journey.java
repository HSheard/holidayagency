package com.hakim.holidayagency.domain;

public class Journey {
    int passengers;
    String homeToAirport;
    String destination;



    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getHomeToAirport() {
        return homeToAirport;
    }

    public void setHomeToAirport(String homeToAirport) {
        this.homeToAirport = homeToAirport;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
