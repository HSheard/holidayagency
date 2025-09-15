package com.hakim.holidayagency.service.JourneyPlannerService;

import com.hakim.holidayagency.domain.Journey;
import com.hakim.holidayagency.domain.JourneySuggestion;
import com.hakim.holidayagency.service.FlightRoutesService.FlightRoutesService;
import com.hakim.holidayagency.service.JourneyPlannerService.JourneyPlannerServiceImpl;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JourneyPlannerServiceImplTest {
    JourneyPlannerServiceImpl target;
    @Mock
    FlightRoutesService flightRoutesService;

    @BeforeEach
    void setup() {
        target = new JourneyPlannerServiceImpl(flightRoutesService);
    }

    @Test
    void quickestOutboundRouteIsReturned() {
        Mockito.when(flightRoutesService.getFlightRoutes()).thenReturn(aFlightRoutesGraph());
        Journey journey = new Journey();
        journey.setPassengers(2);
        journey.setHomeToAirport("A10");
        journey.setDestination("D");


        JourneySuggestion result = target.findQuickestRoute(journey);
        Assertions.assertEquals("AD250", result.getOutboundRoute());
    }

    DirectedWeightedMultigraph<String, DefaultWeightedEdge> aFlightRoutesGraph() {
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> flightRoutesGraph = (DirectedWeightedMultigraph<String, DefaultWeightedEdge>) GraphTypeBuilder.<String, DefaultWeightedEdge>directed().allowingMultipleEdges(true).allowingSelfLoops(false).edgeClass(DefaultWeightedEdge.class).weighted(true).buildGraph();
        flightRoutesGraph.addVertex("A");
        flightRoutesGraph.addVertex("B");
        flightRoutesGraph.addVertex("C");
        flightRoutesGraph.addVertex("D");
        DefaultWeightedEdge ab = flightRoutesGraph.addEdge("A", "B");
        flightRoutesGraph.setEdgeWeight(ab, 100);
        DefaultWeightedEdge bc = flightRoutesGraph.addEdge("B", "C");
        flightRoutesGraph.setEdgeWeight(bc, 100);
        DefaultWeightedEdge cd = flightRoutesGraph.addEdge("C", "D");
        flightRoutesGraph.setEdgeWeight(cd, 100);
        DefaultWeightedEdge ad = flightRoutesGraph.addEdge("A", "D");
        flightRoutesGraph.setEdgeWeight(ad, 250);
        return flightRoutesGraph;
    }
}
