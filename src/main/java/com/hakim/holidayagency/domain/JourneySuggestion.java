package com.hakim.holidayagency.domain;

public class JourneySuggestion {
    Vehicle vehicle;
    double vehicleReturnCost;
    String outboundRoute;
    double outboundCost;
    String inboundRoute;
    double inboundCost;
    double totalCost;

    public JourneySuggestion(Vehicle vehicle, double vehicleReturnCost, String outboundRoute, double outboundCost, String inboundRoute, double inboundCost, double totalCost) {
        this.vehicle = vehicle;
        this.vehicleReturnCost = vehicleReturnCost;
        this.outboundRoute = outboundRoute;
        this.outboundCost = outboundCost;
        this.inboundRoute = inboundRoute;
        this.inboundCost = inboundCost;
        this.totalCost = totalCost;
    }

    public JourneySuggestion() {

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getVehicleReturnCost() {
        return vehicleReturnCost;
    }

    public void setVehicleReturnCost(double vehicleReturnCost) {
        this.vehicleReturnCost = vehicleReturnCost;
    }

    public String getOutboundRoute() {
        return outboundRoute;
    }

    public void setOutboundRoute(String outboundRoute) {
        this.outboundRoute = outboundRoute;
    }

    public double getOutboundCost() {
        return outboundCost;
    }

    public void setOutboundCost(double outboundCost) {
        this.outboundCost = outboundCost;
    }

    public String getInboundRoute() {
        return inboundRoute;
    }

    public void setInboundRoute(String inboundRoute) {
        this.inboundRoute = inboundRoute;
    }

    public double getInboundCost() {
        return inboundCost;
    }

    public void setInboundCost(double inboundCost) {
        this.inboundCost = inboundCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
