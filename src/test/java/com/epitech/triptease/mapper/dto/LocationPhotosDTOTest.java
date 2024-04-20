package com.epitech.triptease.mapper.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LocationPhotosDTOTest {

    @Test
    void testLocationPhotosDTOGetterAndSetter() {
        LocationPhotosDTO locationPhotosDTO = new LocationPhotosDTO();
        locationPhotosDTO.setData(new ArrayList<>());

        assertNotNull(locationPhotosDTO.getData());
    }

    @Test
    void testPhotoDataGetterAndSetter() {
        PhotoData photoData = new PhotoData();
        photoData.setId(1);
        photoData.set_blessed(true);
        photoData.setCaption("Beautiful view");
        photoData.setPublished_date("2024-04-20");
        photoData.setImages(new Images());
        photoData.setAlbum("Vacation");
        photoData.setSource(new Source());
        photoData.setUser(new User());

        assertEquals(1, photoData.getId());
        assertTrue(photoData.is_blessed());
        assertEquals("Beautiful view", photoData.getCaption());
        assertEquals("2024-04-20", photoData.getPublished_date());
        assertNotNull(photoData.getImages());
        assertEquals("Vacation", photoData.getAlbum());
        assertNotNull(photoData.getSource());
        assertNotNull(photoData.getUser());
    }

    @Test
    public void testImagesGetterAndSetter() {
        Images images = new Images();
        images.setThumbnail(new Image());
        images.setSmall(new Image());
        images.setMedium(new Image());
        images.setLarge(new Image());
        images.setOriginal(new Image());

        assertNotNull(images.getThumbnail());
        assertNotNull(images.getSmall());
        assertNotNull(images.getMedium());
        assertNotNull(images.getLarge());
        assertNotNull(images.getOriginal());
    }

    @Test
    public void testImageGetterAndSetter() {
        Image image = new Image();
        image.setHeight(100);
        image.setWidth(200);
        image.setUrl("https://example.com/image.jpg");

        assertEquals(100, image.getHeight());
        assertEquals(200, image.getWidth());
        assertEquals("https://example.com/image.jpg", image.getUrl());
    }

    @Test
    public void testSourceGetterAndSetter() {
        Source source = new Source();
        source.setName("User123");
        source.setLocalized_name("User123");

        assertEquals("User123", source.getName());
        assertEquals("User123", source.getLocalized_name());
    }

    @Test
    void testUserGetterAndSetter() {
        User user = new User();
        user.setUsername("user123");

        assertEquals("user123", user.getUsername());
    }
}
