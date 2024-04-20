package com.epitech.triptease.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationReviewsDTO {
    private List<ReviewData> data;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ReviewData {
    private long id;
    private String lang;
    private int location_id;
    private String published_date;
    private int rating;
    private int helpful_votes;
    private String rating_image_url;
    private String url;
    private String text;
    private String title;
    private String trip_type;
    private String travel_date;
    private UserReview user;
    private SubratingsReview subratings;
    private OwnerResponse owner_response;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class UserReview {
    private String username;
    private UserLocation user_location;
    private Avatar avatar;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class UserLocation {
    private String id;
    private String name;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Avatar {
    private String thumbnail;
    private String small;
    private String medium;
    private String large;
    private String original;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class SubratingsReview {
    private Map<String, SubratingReview> subratings;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class SubratingReview {
    private String name;
    private String rating_image_url;
    private int value;
    private String localized_name;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class OwnerResponse {
    private long id;
    private String title;
    private String text;
    private String lang;
    private String author;
    private String published_date;
}

