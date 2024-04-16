package com.epitech.triptease.controller;

import com.epitech.triptease.mapper.dto.FlightFilterDTO;
import com.epitech.triptease.mapper.dto.FlightOfferResponseDTO;
import com.epitech.triptease.mapper.dto.LocationResponseDTO;
import com.epitech.triptease.mapper.dto.LocationDTO;
import com.epitech.triptease.mapper.dto.TripAdvisorFilterDTO;
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
}
