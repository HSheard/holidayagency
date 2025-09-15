package com.hakim.holidayagency.service.FlightRoutesService;

import com.hakim.holidayagency.service.FlightsDataService.FlightsDataService;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FlightRoutesServiceImplTest {
    @Mock
    FlightsDataService flightsDataService;

    FlightRoutesServiceImpl target;

    @BeforeEach
    void setup(){
        target = new FlightRoutesServiceImpl(flightsDataService);
    }

    @Test
    void populateRoutes_containsCorrectEdge(){
        String[] flightData = {"AB800"};
        Mockito.when(flightsDataService.getFlightsFromDatabase()).thenReturn(flightData);
        DirectedWeightedMultigraph<String, DefaultWeightedEdge> testResult = target.getFlightRoutes();
        DefaultWeightedEdge edge = testResult.getEdge("A", "B");
        Assertions.assertNotNull(edge);
        testResult.getEdgeWeight(edge);
        Assertions.assertEquals(800.0, testResult.getEdgeWeight(edge));
    }

    @Test
    void throwsError_invalidInputData(){
        String[] flightData = {"A GIANT RAT"};
        Mockito.when(flightsDataService.getFlightsFromDatabase()).thenReturn(flightData);
        Exception exception = assertThrows(InputMismatchException.class, () -> {
            target.getFlightRoutes();
        });
        assertEquals("Invalid flight data retrieved from database.", exception.getMessage());
    }
}
