package com.epitech.triptease.mapper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LocationPhotosDTO {
    private List<PhotoData> data;
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class PhotoData {
    private long id;
    private boolean is_blessed;
    private String caption;
    private String published_date;
    private Images images;
    private String album;
    private Source source;
    private User user;
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Images {
    private Image thumbnail;
    private Image small;
    private Image medium;
    private Image large;
    private Image original;
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Image {
    private int height;
    private int width;
    private String url;
    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Source {
    private String name;
    private String localized_name;
    
}
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class User {
    private String username;

}
