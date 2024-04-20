package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationDTOTest {

    @Test
    void testLocationDTOGetterAndSetter() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_id("1");
        locationDTO.setName("Test Location");
        locationDTO.setWeb_url("https://example.com");
        locationDTO.setAddress_obj(new LocationAddress());
        locationDTO.setAncestors(new ArrayList<>());
        locationDTO.setLatitude("40.7128");
        locationDTO.setLongitude("-74.0060");
        locationDTO.setTimezone("America/New_York");
        locationDTO.setPhone("+1-123-456-7890");
        locationDTO.setWebsite("https://testwebsite.com");
        locationDTO.setWrite_review("https://testwebsite.com/write-review");
        locationDTO.setRanking_data(new RankingData());
        locationDTO.setRating("4.5");
        locationDTO.setRating_image_url("https://example.com/rating_image");
        locationDTO.setNum_reviews("1000");
        locationDTO.setReview_rating_count(new ReviewRatingCount());
        locationDTO.setSubratings(new Subratings());
        locationDTO.setPhoto_count("500");
        locationDTO.setSee_all_photos("https://example.com/photos");
        locationDTO.setPrice_level("$$");
        locationDTO.setHours(new Hours());
        locationDTO.setFeatures(new ArrayList<>());
        locationDTO.setCuisine(new ArrayList<>());
        locationDTO.setCategory(new Category());
        locationDTO.setSubcategory(new ArrayList<>());
        locationDTO.setTrip_types(new ArrayList<>());
        locationDTO.setNeighborhood_info(new ArrayList<>());
        locationDTO.setAwards(new ArrayList<>());

        assertEquals("1", locationDTO.getLocation_id());
        assertEquals("Test Location", locationDTO.getName());
        assertEquals("https://example.com", locationDTO.getWeb_url());
        assertNotNull(locationDTO.getAddress_obj());
        assertNotNull(locationDTO.getAncestors());
        assertEquals("40.7128", locationDTO.getLatitude());
        assertEquals("-74.0060", locationDTO.getLongitude());
        assertEquals("America/New_York", locationDTO.getTimezone());
        assertEquals("+1-123-456-7890", locationDTO.getPhone());
        assertEquals("https://testwebsite.com", locationDTO.getWebsite());
        assertEquals("https://testwebsite.com/write-review", locationDTO.getWrite_review());
        assertNotNull(locationDTO.getRanking_data());
        assertEquals("4.5", locationDTO.getRating());
        assertEquals("https://example.com/rating_image", locationDTO.getRating_image_url());
        assertEquals("1000", locationDTO.getNum_reviews());
        assertNotNull(locationDTO.getReview_rating_count());
        assertNotNull(locationDTO.getSubratings());
        assertEquals("500", locationDTO.getPhoto_count());
        assertEquals("https://example.com/photos", locationDTO.getSee_all_photos());
        assertEquals("$$", locationDTO.getPrice_level());
        assertNotNull(locationDTO.getHours());
        assertNotNull(locationDTO.getFeatures());
        assertNotNull(locationDTO.getCuisine());
        assertNotNull(locationDTO.getCategory());
        assertNotNull(locationDTO.getSubcategory());
        assertNotNull(locationDTO.getTrip_types());
        assertNotNull(locationDTO.getNeighborhood_info());
        assertNotNull(locationDTO.getAwards());
    }

    @Test
    void testLocationAddressGetterAndSetter() {
        LocationAddress address = new LocationAddress();
        address.setStreet1("123 Main St");
        address.setStreet2("Apt 101");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setPostalcode("12345");
        address.setAddress_string("123 Main St, Apt 101, City, Country, 12345");

        assertEquals("123 Main St", address.getStreet1());
        assertEquals("Apt 101", address.getStreet2());
        assertEquals("City", address.getCity());
        assertEquals("State", address.getState());
        assertEquals("Country", address.getCountry());
        assertEquals("12345", address.getPostalcode());
        assertEquals("123 Main St, Apt 101, City, Country, 12345", address.getAddress_string());
    }

    @Test
    void testAncestorGetterAndSetter() {
        Ancestor ancestor = new Ancestor();
        ancestor.setLevel("State");
        ancestor.setName("California");
        ancestor.setLocation_id("1");

        assertEquals("State", ancestor.getLevel());
        assertEquals("California", ancestor.getName());
        assertEquals("1", ancestor.getLocation_id());
    }

    @Test
    void testRankingDataGetterAndSetter() {
        RankingData rankingData = new RankingData();
        rankingData.setGeo_location_id("1");
        rankingData.setRanking_string("Ranked #1");
        rankingData.setGeo_location_name("New York");
        rankingData.setRanking_out_of("100");
        rankingData.setRanking("1");

        assertEquals("1", rankingData.getGeo_location_id());
        assertEquals("Ranked #1", rankingData.getRanking_string());
        assertEquals("New York", rankingData.getGeo_location_name());
        assertEquals("100", rankingData.getRanking_out_of());
        assertEquals("1", rankingData.getRanking());
    }

    @Test
    void testReviewRatingCountGetterAndSetter() {
        ReviewRatingCount reviewRatingCount = new ReviewRatingCount();
        reviewRatingCount.set_1("10");
        reviewRatingCount.set_2("20");
        reviewRatingCount.set_3("30");
        reviewRatingCount.set_4("40");
        reviewRatingCount.set_5("50");

        assertEquals("10", reviewRatingCount.get_1());
        assertEquals("20", reviewRatingCount.get_2());
        assertEquals("30", reviewRatingCount.get_3());
        assertEquals("40", reviewRatingCount.get_4());
        assertEquals("50", reviewRatingCount.get_5());
    }

    @Test
    void testSubratingGetterAndSetter() {
        Subrating subrating = new Subrating();
        subrating.setName("Quality");
        subrating.setLocalized_name("Quality");
        subrating.setRating_image_url("https://example.com/rating_image");
        subrating.setValue("4.5");

        assertEquals("Quality", subrating.getName());
        assertEquals("Quality", subrating.getLocalized_name());
        assertEquals("https://example.com/rating_image", subrating.getRating_image_url());
        assertEquals("4.5", subrating.getValue());
    }

    @Test
    void testSubratingsGetterAndSetter() {
        Subratings subratings = new Subratings();
        subratings.set_0(new Subrating());
        subratings.set_1(new Subrating());
        subratings.set_2(new Subrating());
        subratings.set_3(new Subrating());

        assertEquals(new Subrating(), subratings.get_0());
        assertEquals(new Subrating(), subratings.get_1());
        assertEquals(new Subrating(), subratings.get_2());
        assertEquals(new Subrating(), subratings.get_3());
    }


    @Test
    void testHoursGetterAndSetter() {
        Hours hours = new Hours();
        hours.setPeriods(new ArrayList<>());
        hours.setWeekday_text(new ArrayList<>());

        assertNotNull(hours.getPeriods());
        assertNotNull(hours.getWeekday_text());
    }

    @Test
    void testPeriodGetterAndSetter() {
        Period period = new Period();
        period.setOpen(new Open());
        period.setClose(new Close());

        assertNotNull(period.getOpen());
        assertNotNull(period.getClose());
    }

    @Test
    void testOpenGetterAndSetter() {
        Open open = new Open();
        open.setDay(1);
        open.setTime("09:00");

        assertEquals(1, open.getDay());
        assertEquals("09:00", open.getTime());
    }

    @Test
    void testCloseGetterAndSetter() {
        Close close = new Close();
        close.setDay(1);
        close.setTime("17:00");

        assertEquals(1, close.getDay());
        assertEquals("17:00", close.getTime());
    }

    @Test
    void testCuisineGetterAndSetter() {
        Cuisine cuisine = new Cuisine();
        cuisine.setName("Italian");
        cuisine.setLocalized_name("Italian");

        assertEquals("Italian", cuisine.getName());
        assertEquals("Italian", cuisine.getLocalized_name());
    }

    @Test
    void testCategoryGetterAndSetter() {
        Category category = new Category();
        category.setName("Restaurant");
        category.setLocalized_name("Restaurant");

        assertEquals("Restaurant", category.getName());
        assertEquals("Restaurant", category.getLocalized_name());
    }

    @Test
    void testSubcategoryGetterAndSetter() {
        Subcategory subcategory = new Subcategory();
        subcategory.setName("Pizza");
        subcategory.setLocalized_name("Pizza");

        assertEquals("Pizza", subcategory.getName());
        assertEquals("Pizza", subcategory.getLocalized_name());
    }

    @Test
    void testTripTypeGetterAndSetter() {
        TripType tripType = new TripType();
        tripType.setName("Foodie");
        tripType.setLocalized_name("Foodie");
        tripType.setValue("foodie");

        assertEquals("Foodie", tripType.getName());
        assertEquals("Foodie", tripType.getLocalized_name());
        assertEquals("foodie", tripType.getValue());
    }
}
