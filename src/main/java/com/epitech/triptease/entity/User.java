package com.epitech.triptease.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tt_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @NotNull
    @Column
    private String name;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String role;

    @Column
    private String provider;

}
