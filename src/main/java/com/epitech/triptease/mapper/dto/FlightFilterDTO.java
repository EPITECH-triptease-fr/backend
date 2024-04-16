package com.epitech.triptease.mapper.dto;

import lombok.Data;

@Data
public class FlightFilterDTO {

    private String originLocationCode;

    private String destinationLocationCode;

    private String departureDate;

    private String returnDate;

    private String adults;

    private String children;

    private String infants;

    private String travelClass;

    private String nonStop;

    private String currencyCode;

    private String maxPrice;

    private String max;

}
