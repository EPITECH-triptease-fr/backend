package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightFilterDTOTest {

    @Test
    public void testFlightFilterDTO() {
        FlightFilterDTO flightFilterDTO = new FlightFilterDTO();
        flightFilterDTO.setOriginLocationCode("Origin");
        flightFilterDTO.setDestinationLocationCode("Destination");
        flightFilterDTO.setDepartureDate("2024-04-16");
        flightFilterDTO.setReturnDate("2024-04-20");
        flightFilterDTO.setAdults("2");
        flightFilterDTO.setChildren("1");
        flightFilterDTO.setInfants("0");
        flightFilterDTO.setTravelClass("Economy");
        flightFilterDTO.setNonStop("true");
        flightFilterDTO.setCurrencyCode("USD");
        flightFilterDTO.setMaxPrice("500");
        flightFilterDTO.setMax("5");

        String originLocationCode = flightFilterDTO.getOriginLocationCode();
        String destinationLocationCode = flightFilterDTO.getDestinationLocationCode();
        String departureDate = flightFilterDTO.getDepartureDate();
        String returnDate = flightFilterDTO.getReturnDate();
        String adults = flightFilterDTO.getAdults();
        String children = flightFilterDTO.getChildren();
        String infants = flightFilterDTO.getInfants();
        String travelClass = flightFilterDTO.getTravelClass();
        String nonStop = flightFilterDTO.getNonStop();
        String currencyCode = flightFilterDTO.getCurrencyCode();
        String maxPrice = flightFilterDTO.getMaxPrice();
        String max = flightFilterDTO.getMax();

        assertEquals("Origin", originLocationCode);
        assertEquals("Destination", destinationLocationCode);
        assertEquals("2024-04-16", departureDate);
        assertEquals("2024-04-20", returnDate);
        assertEquals("2", adults);
        assertEquals("1", children);
        assertEquals("0", infants);
        assertEquals("Economy", travelClass);
        assertEquals("true", nonStop);
        assertEquals("USD", currencyCode);
        assertEquals("500", maxPrice);
        assertEquals("5", max);
    }
}
