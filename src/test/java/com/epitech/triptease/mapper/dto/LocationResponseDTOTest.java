package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationResponseDTOTest {

    @Test
    void testLocationResponseDTO() {

        LocationResponseDTO locationResponseDTO = new LocationResponseDTO();
        List<LocationData> locationDataList = new ArrayList<>();
        LocationData locationData = new LocationData();
        locationData.setLocation_id("1");
        locationData.setName("Test Location");

        Address address = new Address();
        address.setStreet1("123 Main St");
        address.setStreet2("123 Main St");
        address.setCity("New York");
        address.setCountry("US");
        address.setPostalcode("hey");
        address.setAddress_string("test");
        locationData.setAddress_obj(address);

        locationDataList.add(locationData);
        locationResponseDTO.setData(locationDataList);


        List<LocationData> retrievedLocationDataList = locationResponseDTO.getData();

        assertNotNull(retrievedLocationDataList);
        assertEquals(1, retrievedLocationDataList.size());
        LocationData retrievedLocationData = retrievedLocationDataList.get(0);
        assertEquals("1", retrievedLocationData.getLocation_id());
        assertEquals("Test Location", retrievedLocationData.getName());
        Address retrievedAddress = retrievedLocationData.getAddress_obj();
        assertNotNull(retrievedAddress);
        assertEquals("123 Main St", retrievedAddress.getStreet1());
        assertEquals("New York", retrievedAddress.getCity());
        assertEquals("US", retrievedAddress.getCountry());
        assertEquals("123 Main St", retrievedAddress.getStreet2());
        assertEquals("hey", retrievedAddress.getPostalcode());
        assertEquals("test", retrievedAddress.getAddress_string());
    }
}
