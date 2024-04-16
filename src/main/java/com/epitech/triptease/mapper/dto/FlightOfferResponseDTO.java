package com.epitech.triptease.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FlightOfferResponseDTO {
    private Meta meta;
    private List<FlightOffer> data;
    private Dictionary dictionaries;

}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Meta {
    private String count;
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class FlightOffer {
    private String id;
    private int numberOfBookableSeats;
    private List<Itinerary> itineraries;
    private Price price;
    private List<String> validatingAirlineCodes;
    private List<TravelerPricing> travelerPricings;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Itinerary {
    private String duration;
    private List<Segment> segments;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Segment {
    private Departure departure;
    private Arrival arrival;
    private String carrierCode;
    private String number;
    private Operating operating;
    private String duration;
    private String id;
    private int numberOfStops;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Departure {
    private String iataCode;
    private String at;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Arrival {
    private String iataCode;
    private String at;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Aircraft {
    private String code;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Operating {
    private String carrierCode;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Price {
    private String currency;
    private String grandTotal;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class TravelerPricing {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private Price price;
    private List<FareDetailsBySegment> fareDetailsBySegment;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class FareDetailsBySegment {
    private String segmentId;
    private String cabin;
    private String fareBasis;
    private String _class;
    private IncludedCheckedBags includedCheckedBags;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class IncludedCheckedBags {
    private int quantity;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Dictionary {
    private Map<String, Location> locations;
    private Map<String, String> aircrafts;
    private Map<String, String> currencies;
    private Map<String, String> carriers;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Location {
    private String cityCode;
    private String countryCode;
    
}


