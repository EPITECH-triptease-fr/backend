package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripAdvisorFilterDTOTest {

    @Test
    void testTripAdvisorFilterDTO() {
        TripAdvisorFilterDTO tripAdvisorFilterDTO = new TripAdvisorFilterDTO();
        tripAdvisorFilterDTO.setSearchQuery("New York");
        tripAdvisorFilterDTO.setCategory("Hotels");

        String searchQuery = tripAdvisorFilterDTO.getSearchQuery();
        String category = tripAdvisorFilterDTO.getCategory();

        assertEquals("New York", searchQuery);
        assertEquals("Hotels", category);
    }

}
