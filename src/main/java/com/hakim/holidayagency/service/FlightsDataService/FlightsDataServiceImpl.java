package com.hakim.holidayagency.service.FlightsDataService;

import org.springframework.stereotype.Service;

@Service
public class FlightsDataServiceImpl implements FlightsDataService{
    @Override
    public String[] getFlightsFromDatabase() {
        //TODO: parse from json file
        return new String[]{"AB800", "BC900", "CD400", "DE400",
                "BF400", "CE300", "DE300", "EB600",
                "CE200", "DC700", "EB500", "FD200"};
    }
}
