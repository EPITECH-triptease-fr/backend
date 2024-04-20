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
    void testMetaGetterAndSetter() {
        Meta meta = new Meta();
        meta.setCount("10");
        assertEquals("10", meta.getCount());
    }

    @Test
    void testFlightOfferResponseDTOGetterAndSetter() {
        FlightOfferResponseDTO flightOffer = new FlightOfferResponseDTO();
        flightOffer.setData(new ArrayList<>());
        flightOffer.setMeta(new Meta());
        flightOffer.setDictionaries(new Dictionary());
        assertEquals(new ArrayList<>(), flightOffer.getData());
        assertEquals(new Meta(), flightOffer.getMeta());
        assertEquals(new Dictionary(), flightOffer.getDictionaries());
    }

    @Test
    void testAircraftGetterAndSetter() {
        Aircraft aircraft = new Aircraft();
        aircraft.setCode("10");
        assertEquals("10", aircraft.getCode());
    }

    @Test
    void testOperatingGetterAndSetter() {
        Operating operating = new Operating();
        operating.setCarrierCode("ccc");
        assertEquals("ccc", operating.getCarrierCode());
    }

    @Test
    void testFlightOfferGetterAndSetter() {
        FlightOffer flightOffer = new FlightOffer();
        flightOffer.setId("FO001");
        flightOffer.setNumberOfBookableSeats(100);
        List<Itinerary> itineraries = new ArrayList<>();
        flightOffer.setItineraries(itineraries);
        Price price = new Price();
        flightOffer.setPrice(price);
        List<String> validatingAirlineCodes = new ArrayList<>();
        flightOffer.setValidatingAirlineCodes(validatingAirlineCodes);
        List<TravelerPricing> travelerPricings = new ArrayList<>();
        flightOffer.setTravelerPricings(travelerPricings);

        assertEquals("FO001", flightOffer.getId());
        assertEquals(100, flightOffer.getNumberOfBookableSeats());
        assertEquals(itineraries, flightOffer.getItineraries());
        assertEquals(price, flightOffer.getPrice());
        assertEquals(validatingAirlineCodes, flightOffer.getValidatingAirlineCodes());
        assertEquals(travelerPricings, flightOffer.getTravelerPricings());
    }

    @Test
    void testItineraryGetterAndSetter() {
        Itinerary itinerary = new Itinerary();
        itinerary.setDuration("2h");
        List<Segment> segments = new ArrayList<>();
        itinerary.setSegments(segments);
        assertEquals("2h", itinerary.getDuration());
        assertEquals(segments, itinerary.getSegments());
    }

    @Test
    void testSegmentGetterAndSetter() {
        Segment segment = new Segment();
        Departure departure = new Departure();
        Arrival arrival = new Arrival();
        segment.setDeparture(departure);
        segment.setArrival(arrival);
        segment.setCarrierCode("AA");
        segment.setNumber("123");
        segment.setOperating(new Operating());
        segment.setDuration("1h");
        segment.setId("S001");
        segment.setNumberOfStops(0);

        assertEquals(departure, segment.getDeparture());
        assertEquals(arrival, segment.getArrival());
        assertEquals("AA", segment.getCarrierCode());
        assertEquals("123", segment.getNumber());
        assertNotNull(segment.getOperating());
        assertEquals("1h", segment.getDuration());
        assertEquals("S001", segment.getId());
        assertEquals(0, segment.getNumberOfStops());
    }

    @Test
    void testDepartureGetterAndSetter() {
        Departure departure = new Departure();
        departure.setIataCode("JFK");
        departure.setAt("2024-04-20T12:00:00");

        assertEquals("JFK", departure.getIataCode());
        assertEquals("2024-04-20T12:00:00", departure.getAt());
    }

    @Test
    void testArrivalGetterAndSetter() {
        Arrival arrival = new Arrival();
        arrival.setIataCode("LAX");
        arrival.setAt("2024-04-20T14:00:00");

        assertEquals("LAX", arrival.getIataCode());
        assertEquals("2024-04-20T14:00:00", arrival.getAt());
    }

    @Test
    void testPriceGetterAndSetter() {
        Price price = new Price();
        price.setCurrency("USD");
        price.setGrandTotal("1000");

        assertEquals("USD", price.getCurrency());
        assertEquals("1000", price.getGrandTotal());
    }

    @Test
    void testTravelerPricingGetterAndSetter() {
        TravelerPricing travelerPricing = new TravelerPricing();
        travelerPricing.setTravelerId("1");
        travelerPricing.setFareOption("ECONOMY");
        travelerPricing.setTravelerType("ADULT");
        travelerPricing.setPrice(new Price());
        travelerPricing.setFareDetailsBySegment(new ArrayList<>());

        assertEquals("1", travelerPricing.getTravelerId());
        assertEquals("ECONOMY", travelerPricing.getFareOption());
        assertEquals("ADULT", travelerPricing.getTravelerType());
        assertNotNull(travelerPricing.getPrice());
        assertNotNull(travelerPricing.getFareDetailsBySegment());
    }

    @Test
    void testFareDetailsBySegmentGetterAndSetter() {
        FareDetailsBySegment fareDetailsBySegment = new FareDetailsBySegment();
        fareDetailsBySegment.setSegmentId("S001");
        fareDetailsBySegment.setCabin("ECONOMY");
        fareDetailsBySegment.setFareBasis("FLEX");
        fareDetailsBySegment.set_class("E");

        IncludedCheckedBags includedCheckedBags = new IncludedCheckedBags();
        includedCheckedBags.setQuantity(1);
        fareDetailsBySegment.setIncludedCheckedBags(includedCheckedBags);

        assertEquals("S001", fareDetailsBySegment.getSegmentId());
        assertEquals("ECONOMY", fareDetailsBySegment.getCabin());
        assertEquals("FLEX", fareDetailsBySegment.getFareBasis());
        assertEquals("E", fareDetailsBySegment.get_class());
        assertEquals(1, fareDetailsBySegment.getIncludedCheckedBags().getQuantity());
    }

    @Test
    void testDictionaryGetterAndSetter() {
        Dictionary dictionary = new Dictionary();
        Map<String, Location> locations = new HashMap<>();
        dictionary.setLocations(locations);
        Map<String, String> aircrafts = new HashMap<>();
        dictionary.setAircrafts(aircrafts);
        Map<String, String> currencies = new HashMap<>();
        dictionary.setCurrencies(currencies);
        Map<String, String> carriers = new HashMap<>();
        dictionary.setCarriers(carriers);

        assertEquals(locations, dictionary.getLocations());
        assertEquals(aircrafts, dictionary.getAircrafts());
        assertEquals(currencies, dictionary.getCurrencies());
        assertEquals(carriers, dictionary.getCarriers());
    }

    @Test
    void testLocationGetterAndSetter() {
        Location location = new Location();
        location.setCityCode("NYC");
        location.setCountryCode("US");

        assertEquals("NYC", location.getCityCode());
        assertEquals("US", location.getCountryCode());
    }

    @Test
    void testIncludedCheckedBagsGetterAndSetter() {
        IncludedCheckedBags includedCheckedBags = new IncludedCheckedBags();
        includedCheckedBags.setQuantity(2);

        assertEquals(2, includedCheckedBags.getQuantity());
    }
}
