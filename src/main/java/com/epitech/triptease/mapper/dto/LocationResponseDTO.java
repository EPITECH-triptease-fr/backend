package com.epitech.triptease.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationResponseDTO {

    private List<LocationData> data;

}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class LocationData {
    private String location_id;
    private String name;
    private Address address_obj;

}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Address {
    private String street1;
    private String street2;
    private String city;
    private String country;
    private String postalcode;
    private String address_string;

}
