package com.hakim.holidayagency.service.JourneyPlannerService;

import com.hakim.holidayagency.domain.Journey;
import com.hakim.holidayagency.domain.JourneySuggestion;
import com.hakim.holidayagency.domain.Vehicle;
import com.hakim.holidayagency.service.FlightRoutesService.FlightRoutesService;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JourneyPlannerServiceImpl implements JourneyPlannerService {
    final double FLIGHT_COST_PER_MILE = 0.10;
    final double CAR_COST_PER_MILE = 0.20;
    final double TAXI_COST_PER_MILE = 0.40;
    final double PARKING_CHARGE = 3.00;
    final int MAX_CAR_PASSENGERS = 4;
    final int MAX_TAXI_PASSENGERS = 4;

    FlightRoutesService flightRoutesService;


    public JourneyPlannerServiceImpl(FlightRoutesService flightRoutesService) {
        this.flightRoutesService = flightRoutesService;
    }

    @Override
    public JourneySuggestion findQuickestRoute(Journey journey) {
        JourneySuggestion journeySuggestion = new JourneySuggestion();
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph = flightRoutesService.getFlightRoutes();
        populateOutbound(flightRoutesGraph, journey, journeySuggestion);
        populateInbound(flightRoutesGraph, journey, journeySuggestion);
        populateVehicleCosts(journey, journeySuggestion);
        populateTotalCost(journeySuggestion);
        System.out.println(journey.toString());
        return journeySuggestion;
    }

    private void populateTotalCost(JourneySuggestion journeySuggestion) {
        journeySuggestion.setTotalCost(journeySuggestion.getInboundCost()+ journeySuggestion.getOutboundCost()+ journeySuggestion.getVehicleReturnCost());
    }

    private void populateVehicleCosts(Journey journey, JourneySuggestion journeySuggestion) {
        int milesToAirport = Integer.parseInt(journey.getHomeToAirport().substring(1));
        double taxiCost = calculateTaxiCost(journey.getPassengers(), milesToAirport);
        double carCost = calculateCarCost(journey.getPassengers(), milesToAirport);
        if(taxiCost < carCost){
            journeySuggestion.setVehicle(Vehicle.TAXI);
            journeySuggestion.setVehicleReturnCost(taxiCost);
        }else {
            journeySuggestion.setVehicle(Vehicle.CAR);
            journeySuggestion.setVehicleReturnCost(carCost);
        }
    }

    private double calculateTaxiCost(int passengers, int miles){
        int taxisRequired = (int) Math.ceil((double) passengers /4);
        return 2 * taxisRequired * miles * TAXI_COST_PER_MILE;
    }
    private double calculateCarCost(int passengers, int miles){
        int carsRequired = (int) Math.ceil((double) passengers /4);
        return (carsRequired * PARKING_CHARGE) + (2* carsRequired * miles * CAR_COST_PER_MILE);

    }

    private void populateOutbound(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, Journey journey, JourneySuggestion journeySuggestion) {
        String start = journey.getHomeToAirport().substring(0, 1);
        String destination = journey.getDestination();
        GraphPath<String, DefaultWeightedEdge> shortestPath = findShortestPath(flightRoutesGraph, start, destination);
        if (shortestPath != null) {
            journeySuggestion.setOutboundCost(flightMilesToCost(shortestPath.getWeight(), journey.getPassengers()));
            journeySuggestion.setOutboundRoute(routeToString(flightRoutesGraph, shortestPath));
        } else {
            journeySuggestion.setOutboundCost(0);
            journeySuggestion.setOutboundRoute("No outbound flight");

        }

    }

    private void populateInbound(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, Journey journey, JourneySuggestion journeySuggestion) {
        String start = journey.getHomeToAirport().substring(0, 1);
        String destination = journey.getDestination();
        GraphPath<String, DefaultWeightedEdge> shortestPath = findShortestPath(flightRoutesGraph, destination, start);
        if (shortestPath != null) {
            journeySuggestion.setInboundCost(flightMilesToCost(shortestPath.getWeight(), journey.getPassengers()));
            journeySuggestion.setInboundRoute(routeToString(flightRoutesGraph, shortestPath));
        } else {
            journeySuggestion.setInboundCost(0);
            journeySuggestion.setInboundRoute("No inbound flight");

        }
    }

    private String routeToString(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, GraphPath<String, DefaultWeightedEdge> shortestPath) {
        ArrayList<String> routes = new ArrayList<>();
        for (DefaultWeightedEdge defaultWeightedEdge : shortestPath.getEdgeList()) {
            String route = String.join(flightRoutesGraph.getEdgeTarget(defaultWeightedEdge),
                    flightRoutesGraph.getEdgeSource(defaultWeightedEdge),
                    String.valueOf((int) flightRoutesGraph.getEdgeWeight(defaultWeightedEdge)));
            routes.add(route);
        }
        return String.join("--", routes);
    }

    private GraphPath<String, DefaultWeightedEdge> findShortestPath(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, String start, String destination) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(flightRoutesGraph);
        return dijkstraShortestPath
                .getPath(start, destination);
    }

    private double flightMilesToCost(double flightMiles, int passengers) {
        return flightMiles * passengers * FLIGHT_COST_PER_MILE;
    }
}
