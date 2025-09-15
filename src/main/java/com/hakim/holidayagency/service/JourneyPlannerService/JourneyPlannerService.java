package com.hakim.holidayagency.service.JourneyPlannerService;

import com.hakim.holidayagency.domain.Journey;
import com.hakim.holidayagency.domain.JourneySuggestion;
import org.springframework.stereotype.Service;

@Service
public interface JourneyPlannerService {
    JourneySuggestion findQuickestRoute(Journey journey);
}
