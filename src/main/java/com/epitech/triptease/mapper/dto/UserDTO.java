package com.epitech.triptease.mapper.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {

    @Id
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String role;

    private String provider;
}
