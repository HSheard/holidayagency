package com.hakim.holidayagency.service.FlightRoutesService;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Service;

@Service
public interface FlightRoutesService {
    DirectedWeightedMultigraph<String, DefaultWeightedEdge> getFlightRoutes();
}
