package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ContactInfoDto {

    private UUID id;

    private String country;

    private String city;

    private String street;

    private String building;

    private String flat;

    private String phoneNumber;

    private String email;
}
