package com.hakim.holidayagency.service.JourneyPlannerService;

import com.hakim.holidayagency.domain.Journey;
import com.hakim.holidayagency.domain.JourneySuggestion;
import com.hakim.holidayagency.service.FlightRoutesService.FlightRoutesService;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyPlannerServiceImpl implements JourneyPlannerService{
    FlightRoutesService flightRoutesService;
    public JourneyPlannerServiceImpl(FlightRoutesService flightRoutesService){
        this.flightRoutesService = flightRoutesService;
    }
    @Override
    public JourneySuggestion findQuickestRoute(Journey journey) {
        JourneySuggestion journeySuggestion = new JourneySuggestion();
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph = flightRoutesService.getFlightRoutes();
        populateOutbound(flightRoutesGraph,journey, journeySuggestion);
        return null;
    }

    private void populateOutbound(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, Journey journey, JourneySuggestion journeySuggestion) {
        String start = journey.getHomeToAirport().substring(0,1);
        String destination = journey.getDestination();
        GraphPath<String, DefaultWeightedEdge> shortestPath = findShortestPath(flightRoutesGraph, start, destination);
        journeySuggestion.setOutboundCost(shortestPath.getWeight());
        StringBuilder outboundRoute = new StringBuilder();
        for (DefaultWeightedEdge defaultWeightedEdge : shortestPath.getEdgeList()) {
            outboundRoute.append(flightRoutesGraph.getEdgeSource(defaultWeightedEdge))
                    .append(flightRoutesGraph.getEdgeTarget(defaultWeightedEdge))
                    .append(flightRoutesGraph.getEdgeWeight(defaultWeightedEdge))
                    .append("--");
        }
        System.out.println(outboundRoute);
        System.out.println(outboundRoute);
    }

    private GraphPath<String, DefaultWeightedEdge> findShortestPath(DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph, String start, String destination){
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(flightRoutesGraph);

        GraphPath<String, DefaultWeightedEdge> shortestPath = dijkstraShortestPath
                .getPath(start, destination);
        System.out.println(shortestPath);
        return shortestPath;
    }

    private String routeToString(List<String> shortestPath){
        return null;
    }

    private double calculateRouteCost(){
        return 0;
    }
}
