package com.hakim.holidayagency.controllers;

import com.hakim.holidayagency.domain.JourneySuggestion;
import com.hakim.holidayagency.domain.Request.FindQuickestRequest;
import com.hakim.holidayagency.service.JourneyPlannerService.JourneyPlannerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JourneyController {
    private final JourneyPlannerService journeyPlannerService;

    public JourneyController(JourneyPlannerService journeyPlannerService){
        this.journeyPlannerService = journeyPlannerService;
    }
    @PostMapping("/find-quickest")
    public JourneySuggestion findQuickest(@RequestBody FindQuickestRequest request) {
        return journeyPlannerService.findQuickestRoute(request.getJourney());
    }

}
