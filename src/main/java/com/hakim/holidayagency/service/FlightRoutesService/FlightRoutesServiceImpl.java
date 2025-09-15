package com.hakim.holidayagency.service.FlightRoutesService;

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

@Service
public class FlightRoutesServiceImpl implements FlightRoutesService {
    @Override
    public DirectedWeightedMultigraph<String, DefaultWeightedEdge> getFlightRoutes() {
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph = (DirectedWeightedMultigraph<String, DefaultWeightedEdge>) GraphTypeBuilder.<String, DefaultWeightedEdge>directed().allowingMultipleEdges(true).allowingSelfLoops(false).edgeClass(DefaultWeightedEdge.class).weighted(true).buildGraph();
        String[] flights = retrieveFlightsFromFile();
        for (String flight : flights) {
            //The addVertex method checks whether a vertex already exists
            String routeStart = flight.substring(0,1);
            String routeEnd = flight.substring(1,2);
            double routeWeight = Double.parseDouble(flight.substring(2));
            flightRoutesGraph.addVertex(routeStart);
            flightRoutesGraph.addVertex(routeEnd);
            DefaultWeightedEdge routeEdge = flightRoutesGraph.addEdge(routeStart, routeEnd);
            flightRoutesGraph.setEdgeWeight(routeEdge,routeWeight);
        }
        printGraph(flightRoutesGraph);
        return flightRoutesGraph;
    }

    private String[] retrieveFlightsFromFile(){
        //TODO: parse from json file
        return new String[]{"AB800", "BC900", "CD400", "DE400",
                "BF400", "CE300", "DE300", "EB600",
                "CE200", "DC700", "EB500", "FD200"};
    }

    private void printGraph(DirectedWeightedMultigraph<String, DefaultWeightedEdge>  graph){
        for(DefaultWeightedEdge e : graph.edgeSet()){
            System.out.println(graph.getEdgeSource(e) + " --> " + graph.getEdgeTarget(e)+" : " +graph.getEdgeWeight(e));
        }

    }
}
