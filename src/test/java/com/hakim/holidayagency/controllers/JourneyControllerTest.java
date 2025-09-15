package com.hakim.holidayagency.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class JourneyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_givenValidInputCheapestJourneysReturned() throws Exception {
        String requestJson= "{ \"journey\": { \"passengers\": 2, \"homeToAirport\": \"B20\", \"destination\": \"D\" } }";

        mockMvc.perform(MockMvcRequestBuilders.post("/find-quickest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicle").value("CAR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleReturnCost").value(11.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outboundRoute").value("BF400--FD200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outboundCost").value(120.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inboundRoute").value("DE300--EB500"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inboundCost").value(160.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value(291.0));
    }
}
