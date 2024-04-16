package com.epitech.triptease.service;

import com.epitech.triptease.mapper.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class TripadvisorService {

    @Value("${AMADEUS_CLIENT_ID}")
    private String clientId;
    @Value("${AMADEUS_CLIENT_SECRET}")
    private String clientSecret;
    @Value("${TRIPADVISOR_API_KEY}")
    private String tripAdvisorApiKey;

    private String amadeusGetFlightOffersUrl = "https://test.api.amadeus.com/v2/shopping/flight-offers";
    private String amadeusGetTokenUrl = "https://test.api.amadeus.com/v1/security/oauth2/token";
    private String tripAdvisorGetLocationsUrl = "https://api.content.tripadvisor.com/api/v1/location/search";
    private String tripAdvisorGetLocationUrl = "https://api.content.tripadvisor.com/api/v1/location/";

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();


    public FlightOfferResponseDTO getFlightsByCriteria(FlightFilterDTO flightFilterDTO, String accessToken) {

        String apiUrl = String.format("%s?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&returnDate=%s&adults=%s&children=%s&infants=%s&travelClass=%s&nonStop=%s&currencyCode=%s&maxPrice=%s&max=%s",
                amadeusGetFlightOffersUrl,
                flightFilterDTO.getOriginLocationCode(),
                flightFilterDTO.getDestinationLocationCode(),
                flightFilterDTO.getDepartureDate(),
                flightFilterDTO.getReturnDate(),
                flightFilterDTO.getAdults(),
                flightFilterDTO.getChildren(),
                flightFilterDTO.getInfants(),
                flightFilterDTO.getTravelClass(),
                flightFilterDTO.getNonStop(),
                flightFilterDTO.getCurrencyCode(),
                flightFilterDTO.getMaxPrice(),
                flightFilterDTO.getMax());

        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        FlightOfferResponseDTO flightOfferResponse;
        try {
            flightOfferResponse = objectMapper.readValue(responseEntity.getBody(), FlightOfferResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return flightOfferResponse;
    }

    public String getAmadeusToken() {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(amadeusGetTokenUrl, requestEntity, Map.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = responseEntity.getBody();
            if (responseBody != null && responseBody.containsKey("access_token")) {
                return (String) responseBody.get("access_token");
            } else {
                throw new RuntimeException("Bearer token not found in response body");
            }
        } else {
            throw new RuntimeException("Failed to obtain bearer token. Status code: " + responseEntity.getStatusCodeValue());
        }
    }


    public LocationResponseDTO getLocationByCriteria(TripAdvisorFilterDTO tripAdvisorFilterDTO) {

        tripAdvisorFilterDTO.setSearchQuery(tripAdvisorFilterDTO.getSearchQuery().replaceAll("\\s+", "%20"));

        String apiUrl = String.format("%s/details?key=%s&language=fr&currency=EUR",
                tripAdvisorGetLocationUrl,
                tripAdvisorApiKey,
                tripAdvisorFilterDTO.getSearchQuery(),
                tripAdvisorFilterDTO.getCategory());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        LocationResponseDTO locationResponse;
        try {
            locationResponse = objectMapper.readValue(responseEntity.getBody(), LocationResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return locationResponse;
    }

    public LocationDTO getLocationById(String locationId) {

        String apiUrl = String.format("%s%s/details?key=%s&language=fr&currency=EUR",
                tripAdvisorGetLocationUrl,
                locationId,
                tripAdvisorApiKey);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        LocationDTO locationDTO;
        try {
            locationDTO = objectMapper.readValue(responseEntity.getBody(), LocationDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return locationDTO;
    }

}
