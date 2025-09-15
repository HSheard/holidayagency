package com.hakim.holidayagency.service.FlightRoutesService;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.hakim.holidayagency.service.FlightsDataService.FlightsDataService;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FlightRoutesServiceImpl implements FlightRoutesService {
    private final FlightsDataService flightsDataService;

    public FlightRoutesServiceImpl(FlightsDataService flightsDataService) {
        this.flightsDataService = flightsDataService;
    }

    /**
     * Get list of flights from data service and for each flight:
     *  - Create vertices for any airport mentioned ("A", "B" etc)
     *  - store the start + end airport as the source and target of an edge
     *  - store the distance in miles as the weight of the edge
     *  - repeat for each flight
     * @return Directed Multigraph where each vertex represents an airport and
     * the weight of an edge represents the distance of the flight in miles.
     */
    @Override
    public DirectedWeightedMultigraph<String, DefaultWeightedEdge> getFlightRoutes() {
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph = (DirectedWeightedMultigraph<String, DefaultWeightedEdge>) GraphTypeBuilder.<String, DefaultWeightedEdge>directed().allowingMultipleEdges(true).allowingSelfLoops(false).edgeClass(DefaultWeightedEdge.class).weighted(true).buildGraph();
        String[] flights = flightsDataService.getFlightsFromDatabase();
        for (String flight : flights) {
            validateFlightData(flight);
            //The addVertex method checks whether a vertex already exists
            String routeStart = flight.substring(0, 1);
            String routeEnd = flight.substring(1, 2);
            double routeWeight = Double.parseDouble(flight.substring(2));
            flightRoutesGraph.addVertex(routeStart);
            flightRoutesGraph.addVertex(routeEnd);
            DefaultWeightedEdge routeEdge = flightRoutesGraph.addEdge(routeStart, routeEnd);
            flightRoutesGraph.setEdgeWeight(routeEdge, routeWeight);
        }
        printGraph(flightRoutesGraph);
        return flightRoutesGraph;
    }

    private void printGraph(DirectedWeightedMultigraph<String, DefaultWeightedEdge> graph) {
        for (DefaultWeightedEdge e : graph.edgeSet()) {
            System.out.println(graph.getEdgeSource(e) + " --> " + graph.getEdgeTarget(e) + " : " + graph.getEdgeWeight(e));
        }
    }

    private void validateFlightData(String flight) {
        final Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z]\\d*$");
        final Matcher matcher = pattern.matcher(flight);
        if (!matcher.matches()) {
            throw new InputMismatchException("Invalid flight data retrieved from database.");
        }
    }
}
