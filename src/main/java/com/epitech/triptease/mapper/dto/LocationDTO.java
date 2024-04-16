package com.epitech.triptease.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationDTO {
    private String location_id;
    private String name;
    private String web_url;
    private LocationAddress address_obj;
    private List<Ancestor> ancestors;
    private String latitude;
    private String longitude;
    private String timezone;
    private String phone;
    private String website;
    private String write_review;
    private RankingData ranking_data;
    private String rating;
    private String rating_image_url;
    private String num_reviews;
    private ReviewRatingCount review_rating_count;
    private Subratings subratings;
    private String photo_count;
    private String see_all_photos;
    private String price_level;
    private Hours hours;
    private List<String> features;
    private List<Cuisine> cuisine;
    private Category category;
    private List<Subcategory> subcategory;
    private List<TripType> trip_types;
    private List<Object> neighborhood_info;
    private List<Object> awards;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class LocationAddress {
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private String postalcode;
    private String address_string;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Ancestor {
    private String level;
    private String name;
    private String location_id;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class RankingData {
    private String geo_location_id;
    private String ranking_string;
    private String geo_location_name;
    private String ranking_out_of;
    private String ranking;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ReviewRatingCount {
    private String _1;
    private String _2;
    private String _3;
    private String _4;
    private String _5;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Subratings {
    private Subrating _0;
    private Subrating _1;
    private Subrating _2;
    private Subrating _3;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Subrating {
    private String name;
    private String localized_name;
    private String rating_image_url;
    private String value;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Hours {
    private List<Period> periods;
    private List<String> weekday_text;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Period {
    private Open open;
    private Close close;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Open {
    private int day;
    private String time;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Close {
    private int day;
    private String time;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Cuisine {
    private String name;
    private String localized_name;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Category {
    private String name;
    private String localized_name;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Subcategory {
    private String name;
    private String localized_name;

    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class TripType {
    private String name;
    private String localized_name;
    private String value;

}
