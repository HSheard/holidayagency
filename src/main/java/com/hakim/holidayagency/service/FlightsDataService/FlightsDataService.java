package com.hakim.holidayagency.service.FlightsDataService;

import org.springframework.stereotype.Service;

@Service
public interface FlightsDataService {
    String[] getFlightsFromDatabase();
}
