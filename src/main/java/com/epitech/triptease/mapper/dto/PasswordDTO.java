package com.epitech.triptease.mapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {

    @NotNull
    private Long user_id;

    @NotBlank
    private String password;

}