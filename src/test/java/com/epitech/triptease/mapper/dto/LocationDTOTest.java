package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationDTOTest {

    @Test
    public void testLocationDTO() {

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_id("1");
        locationDTO.setName("Test Location");
        locationDTO.setLatitude("40.7128");
        locationDTO.setLongitude("-74.0060");

        LocationAddress address = new LocationAddress();
        address.setStreet1("123 Main St");
        address.setCity("New York");
        address.setState("NY");
        address.setCountry("US");
        locationDTO.setAddress_obj(address);

        List<Ancestor> ancestors = new ArrayList<>();
        Ancestor ancestor = new Ancestor();
        ancestor.setLevel("Country");
        ancestor.setName("United States");
        ancestor.setLocation_id("US");
        ancestors.add(ancestor);
        locationDTO.setAncestors(ancestors);

        RankingData rankingData = new RankingData();
        rankingData.setRanking("1");
        locationDTO.setRanking_data(rankingData);

        Subratings subratings = new Subratings();
        Subrating subrating = new Subrating();
        subrating.setName("Rating");
        subrating.setValue("5");
        subratings.set_0(subrating);
        locationDTO.setSubratings(subratings);

        String locationId = locationDTO.getLocation_id();
        String name = locationDTO.getName();
        String latitude = locationDTO.getLatitude();
        String longitude = locationDTO.getLongitude();
        LocationAddress retrievedAddress = locationDTO.getAddress_obj();
        List<Ancestor> retrievedAncestors = locationDTO.getAncestors();
        RankingData retrievedRankingData = locationDTO.getRanking_data();
        Subratings retrievedSubratings = locationDTO.getSubratings();

        assertEquals("1", locationId);
        assertEquals("Test Location", name);
        assertEquals("40.7128", latitude);
        assertEquals("-74.0060", longitude);
        assertNotNull(retrievedAddress);
        assertEquals("123 Main St", retrievedAddress.getStreet1());
        assertEquals("New York", retrievedAddress.getCity());
        assertEquals("NY", retrievedAddress.getState());
        assertEquals("US", retrievedAddress.getCountry());
        assertNotNull(retrievedAncestors);
        assertEquals(1, retrievedAncestors.size());
        Ancestor retrievedAncestor = retrievedAncestors.get(0);
        assertEquals("Country", retrievedAncestor.getLevel());
        assertEquals("United States", retrievedAncestor.getName());
        assertEquals("US", retrievedAncestor.getLocation_id());
        assertNotNull(retrievedRankingData);
        assertEquals("1", retrievedRankingData.getRanking());
        assertNotNull(retrievedSubratings);
        assertEquals("Rating", retrievedSubratings.get_0().getName());
        assertEquals("5", retrievedSubratings.get_0().getValue());
    }
}
