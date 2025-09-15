package com.hakim.holidayagency.service.JourneyPlannerService;

import com.hakim.holidayagency.service.FlightRoutesService.FlightRoutesService;
import com.hakim.holidayagency.service.JourneyPlannerService.JourneyPlannerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JourneyPlannerServiceImplTest {
    JourneyPlannerServiceImpl target;
    @Mock
    FlightRoutesService flightRoutesService;

    @BeforeEach
    void setup(){
        target = new JourneyPlannerServiceImpl(flightRoutesService);
    }

    @Test
    void populateRoutes_containsCorrectVertex(){

    }
}
