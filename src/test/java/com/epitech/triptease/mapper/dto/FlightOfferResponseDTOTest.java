package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlightOfferResponseDTOTest {

    @Test
    public void testFlightOfferResponseDTO() {

        FlightOfferResponseDTO flightOfferResponseDTO = new FlightOfferResponseDTO();
        Meta meta = new Meta();
        meta.setCount("10");
        flightOfferResponseDTO.setMeta(meta);

        List<FlightOffer> flightOffers = new ArrayList<>();
        FlightOffer flightOffer = new FlightOffer();
        flightOffer.setId("123");
        flightOffer.setNumberOfBookableSeats(2);

        List<Itinerary> itineraries = new ArrayList<>();
        Itinerary itinerary = new Itinerary();
        itinerary.setDuration("PT2H");
        List<Segment> segments = new ArrayList<>();
        Segment segment = new Segment();
        segment.setDuration("PT1H");
        segments.add(segment);
        itinerary.setSegments(segments);
        itineraries.add(itinerary);
        flightOffer.setItineraries(itineraries);

        Price price = new Price();
        price.setCurrency("USD");
        price.setGrandTotal("200");
        flightOffer.setPrice(price);

        List<TravelerPricing> travelerPricings = new ArrayList<>();
        TravelerPricing travelerPricing = new TravelerPricing();
        travelerPricing.setTravelerId("1");
        travelerPricing.setFareOption("Economy");
        travelerPricing.setTravelerType("Adult");
        Price travelerPrice = new Price();
        travelerPrice.setCurrency("USD");
        travelerPrice.setGrandTotal("150");
        travelerPricing.setPrice(travelerPrice);
        travelerPricings.add(travelerPricing);
        flightOffer.setTravelerPricings(travelerPricings);

        flightOffers.add(flightOffer);
        flightOfferResponseDTO.setData(flightOffers);

        Dictionary dictionary = new Dictionary();
        Map<String, Location> locations = new HashMap<>();
        Location location = new Location();
        location.setCityCode("NYC");
        location.setCountryCode("US");
        locations.put("JFK", location);
        dictionary.setLocations(locations);
        flightOfferResponseDTO.setDictionaries(dictionary);

        Meta retrievedMeta = flightOfferResponseDTO.getMeta();
        List<FlightOffer> retrievedFlightOffers = flightOfferResponseDTO.getData();
        Dictionary retrievedDictionary = flightOfferResponseDTO.getDictionaries();

        assertNotNull(retrievedMeta);
        assertEquals("10", retrievedMeta.getCount());

        assertNotNull(retrievedFlightOffers);
        assertEquals(1, retrievedFlightOffers.size());
        FlightOffer retrievedFlightOffer = retrievedFlightOffers.get(0);
        assertEquals("123", retrievedFlightOffer.getId());
        assertEquals(2, retrievedFlightOffer.getNumberOfBookableSeats());

        assertNotNull(retrievedDictionary);
        Map<String, Location> retrievedLocations = retrievedDictionary.getLocations();
        assertNotNull(retrievedLocations);
        assertEquals(1, retrievedLocations.size());
        Location retrievedLocation = retrievedLocations.get("JFK");
        assertNotNull(retrievedLocation);
        assertEquals("NYC", retrievedLocation.getCityCode());
        assertEquals("US", retrievedLocation.getCountryCode());
    }
}
