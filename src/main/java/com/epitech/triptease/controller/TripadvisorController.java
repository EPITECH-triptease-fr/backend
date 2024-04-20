package com.epitech.triptease.controller;

import com.epitech.triptease.mapper.dto.*;
import com.epitech.triptease.service.TripadvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/trips")
@RestController
public class TripadvisorController {

    private final TripadvisorService tripadvisorService;

    @PostMapping("/getFlight")
    public FlightOfferResponseDTO getFlightByCriteria(@RequestBody FlightFilterDTO flightFilterDTO) {
        String accessToken = tripadvisorService.getAmadeusToken();
        if(accessToken != null){
            return tripadvisorService.getFlightsByCriteria(flightFilterDTO, accessToken);
        }
        return null;
    }

    @PostMapping("/getLocations")
    public LocationResponseDTO getLocationByCriteria(@RequestBody TripAdvisorFilterDTO tripAdvisorFilterDTO){
        return tripadvisorService.getLocationByCriteria(tripAdvisorFilterDTO);
    }

    @PostMapping("/getLocation/{locationId}")
    public LocationDTO getLocationById(@PathVariable(value = "locationId") String locationId) {
        return tripadvisorService.getLocationById(locationId);
    }

    @GetMapping("/getLocationPhotos/{locationId}")
    public LocationPhotosDTO getLocationPhotosById(@PathVariable(value = "locationId") String locationId) {
        return tripadvisorService.getLocationPhotosById(locationId);
    }

    @GetMapping("/getLocationReviews/{locationId}")
    public LocationReviewsDTO getLocationReviewsById(@PathVariable(value = "locationId") String locationId) {
        return tripadvisorService.getLocationReviewsById(locationId);
    }
}
