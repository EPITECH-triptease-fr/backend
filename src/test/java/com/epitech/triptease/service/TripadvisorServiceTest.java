package com.epitech.triptease.service;

import com.epitech.triptease.mapper.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TripadvisorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TripadvisorService tripadvisorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFlightsByCriteria() throws JsonProcessingException {
        FlightFilterDTO flightFilterDTO = new FlightFilterDTO();
        flightFilterDTO.setOriginLocationCode("LAX");
        flightFilterDTO.setDestinationLocationCode("JFK");

        String accessToken = "test_access_token";
        FlightOfferResponseDTO mockedResponse = new FlightOfferResponseDTO();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(mockedResponse));
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        FlightOfferResponseDTO flightOfferResponseDTO = tripadvisorService.getFlightsByCriteria(flightFilterDTO, accessToken);

        assertEquals(mockedResponse.toString(), flightOfferResponseDTO.toString());
    }


    @Test
    void testGetAmadeusToken() {
        String expectedToken = "test_token";
        ResponseEntity<Map> responseEntity = ResponseEntity.ok().body(Collections.singletonMap("access_token", expectedToken));
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(Map.class))).thenReturn(responseEntity);

        String token = tripadvisorService.getAmadeusToken();

        assertEquals(expectedToken, token);
    }

    @Test
    void testGetLocationByCriteria() throws JsonProcessingException {
        TripAdvisorFilterDTO tripAdvisorFilterDTO = new TripAdvisorFilterDTO();
        tripAdvisorFilterDTO.setSearchQuery("New York");
        String mockedResponse = "{\"data\":[]}"; // Mocked JSON response
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().body(mockedResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        LocationResponseDTO locationResponseDTO = tripadvisorService.getLocationByCriteria(tripAdvisorFilterDTO);

        assertEquals(mockedResponse, new ObjectMapper().writeValueAsString(locationResponseDTO));
    }

    @Test
    void testGetLocationById() {
        String locationId = "123";
        String expectedName = "New York";
        String mockedResponse = "{\"location_id\": \"123\", \"name\": \"New York\"}";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().body(mockedResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        LocationDTO locationDTO = tripadvisorService.getLocationById(locationId);

        assertEquals(expectedName, locationDTO.getName());
    }

    @Test
    void testGetLocationPhotosById() throws JsonProcessingException {
        String locationId = "123";
        String mockedResponse = "{\"data\":null}"; // Mocked JSON response
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().body(mockedResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        LocationPhotosDTO locationPhotosDTO = tripadvisorService.getLocationPhotosById(locationId);

        assertEquals(mockedResponse, new ObjectMapper().writeValueAsString(locationPhotosDTO));
    }

    @Test
    void testGetLocationReviewsById() throws JsonProcessingException {
        String locationId = "123";
        String mockedResponse = "{\"data\":null}";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = ResponseEntity.ok().body(mockedResponse);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(responseEntity);

        LocationReviewsDTO locationReviewsDTO = tripadvisorService.getLocationReviewsById(locationId);

        assertEquals(mockedResponse, new ObjectMapper().writeValueAsString(locationReviewsDTO));
    }

}
