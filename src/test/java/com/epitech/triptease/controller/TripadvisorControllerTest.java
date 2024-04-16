package com.epitech.triptease.controller;

import com.epitech.triptease.mapper.dto.*;
import com.epitech.triptease.service.TripadvisorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripadvisorControllerTest {

    @Mock
    private TripadvisorService tripadvisorService;

    @InjectMocks
    private TripadvisorController tripadvisorController;

    @Test
    public void testGetFlightByCriteria() {
        FlightFilterDTO flightFilterDTO = new FlightFilterDTO();
        String accessToken = "test_access_token";
        FlightOfferResponseDTO expectedResponse = new FlightOfferResponseDTO();
        when(tripadvisorService.getAmadeusToken()).thenReturn(accessToken);
        when(tripadvisorService.getFlightsByCriteria(any(FlightFilterDTO.class), eq(accessToken))).thenReturn(expectedResponse);

        FlightOfferResponseDTO response = tripadvisorController.getFlightByCriteria(flightFilterDTO);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetLocationByCriteria() {
        TripAdvisorFilterDTO tripAdvisorFilterDTO = new TripAdvisorFilterDTO();
        LocationResponseDTO expectedResponse = new LocationResponseDTO();
        when(tripadvisorService.getLocationByCriteria(any(TripAdvisorFilterDTO.class))).thenReturn(expectedResponse);

        LocationResponseDTO response = tripadvisorController.getLocationByCriteria(tripAdvisorFilterDTO);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testGetLocationById() {
        String locationId = "test_location_id";
        LocationDTO expectedResponse = new LocationDTO();
        when(tripadvisorService.getLocationById(eq(locationId))).thenReturn(expectedResponse);

        LocationDTO response = tripadvisorController.getLocationById(locationId);

        assertEquals(expectedResponse, response);
    }
}
