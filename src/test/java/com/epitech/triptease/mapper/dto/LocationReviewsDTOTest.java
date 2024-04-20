package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LocationReviewsDTOTest {

    @Test
    void testLocationReviewsDTOGetterAndSetter() {
        LocationReviewsDTO locationReviewsDTO = new LocationReviewsDTO();
        locationReviewsDTO.setData(new ArrayList<>());

        assertNotNull(locationReviewsDTO.getData());
    }

    @Test
    void testReviewDataGetterAndSetter() {
        ReviewData reviewData = new ReviewData();
        reviewData.setId(1);
        reviewData.setLang("en");
        reviewData.setLocation_id(123);
        reviewData.setPublished_date("2024-04-20");
        reviewData.setRating(5);
        reviewData.setHelpful_votes(10);
        reviewData.setRating_image_url("https://example.com/rating_image");
        reviewData.setUrl("https://example.com/review");
        reviewData.setText("Great experience!");
        reviewData.setTitle("Amazing place");
        reviewData.setTrip_type("Solo");
        reviewData.setTravel_date("2024-04-15");
        reviewData.setUser(new UserReview());
        reviewData.setSubratings(new SubratingsReview());
        reviewData.setOwner_response(new OwnerResponse());

        assertEquals(1, reviewData.getId());
        assertEquals("en", reviewData.getLang());
        assertEquals(123, reviewData.getLocation_id());
        assertEquals("2024-04-20", reviewData.getPublished_date());
        assertEquals(5, reviewData.getRating());
        assertEquals(10, reviewData.getHelpful_votes());
        assertEquals("https://example.com/rating_image", reviewData.getRating_image_url());
        assertEquals("https://example.com/review", reviewData.getUrl());
        assertEquals("Great experience!", reviewData.getText());
        assertEquals("Amazing place", reviewData.getTitle());
        assertEquals("Solo", reviewData.getTrip_type());
        assertEquals("2024-04-15", reviewData.getTravel_date());
        assertNotNull(reviewData.getUser());
        assertNotNull(reviewData.getSubratings());
        assertNotNull(reviewData.getOwner_response());
    }

    @Test
    void testUserReviewGetterAndSetter() {
        UserReview userReview = new UserReview();
        userReview.setUsername("user123");
        userReview.setUser_location(new UserLocation());
        userReview.setAvatar(new Avatar());

        assertEquals("user123", userReview.getUsername());
        assertNotNull(userReview.getUser_location());
        assertNotNull(userReview.getAvatar());
    }

    @Test
    void testAvatarGetterAndSetter() {
        Avatar avatar = new Avatar();
        avatar.setThumbnail("thumbnail");
        avatar.setSmall("small");
        avatar.setMedium("medium");
        avatar.setLarge("large");
        avatar.setOriginal("original");

        assertEquals("thumbnail", avatar.getThumbnail());
        assertEquals("small", avatar.getSmall());
        assertEquals("medium", avatar.getMedium());
        assertEquals("large", avatar.getLarge());
        assertEquals("original", avatar.getOriginal());
    }

    @Test
    void testUserLocationGetterAndSetter() {
        UserLocation userLocation = new UserLocation();
        userLocation.setId("pol");
        userLocation.setName("ak");

        assertEquals("pol", userLocation.getId());
        assertEquals("ak", userLocation.getName());
    }

    @Test
    void testSubratingsReviewGetterAndSetter() {
        SubratingsReview subratingsReview = new SubratingsReview();
        subratingsReview.setSubratings(new HashMap<>());

        assertNotNull(subratingsReview.getSubratings());
    }

    @Test
    void testSubratingReviewGetterAndSetter() {
        SubratingReview subratingReview = new SubratingReview();
        subratingReview.setName("cleanliness");
        subratingReview.setRating_image_url("https://example.com/cleanliness_rating");
        subratingReview.setValue(4);
        subratingReview.setLocalized_name("Cleanliness");

        assertEquals("cleanliness", subratingReview.getName());
        assertEquals("https://example.com/cleanliness_rating", subratingReview.getRating_image_url());
        assertEquals(4, subratingReview.getValue());
        assertEquals("Cleanliness", subratingReview.getLocalized_name());
    }

    @Test
    void testOwnerResponseGetterAndSetter() {
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setId(1);
        ownerResponse.setTitle("Response Title");
        ownerResponse.setText("Response Text");
        ownerResponse.setLang("en");
        ownerResponse.setAuthor("Owner");
        ownerResponse.setPublished_date("2024-04-21");

        assertEquals(1, ownerResponse.getId());
        assertEquals("Response Title", ownerResponse.getTitle());
        assertEquals("Response Text", ownerResponse.getText());
        assertEquals("en", ownerResponse.getLang());
        assertEquals("Owner", ownerResponse.getAuthor());
        assertEquals("2024-04-21", ownerResponse.getPublished_date());
    }
}
