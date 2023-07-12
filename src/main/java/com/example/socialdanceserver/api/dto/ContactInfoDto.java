package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactInfoDto {

    private String country;

    private String city;

    private String address;

    private String latitude;

    private String longitude;

    private String phoneNumber;

    private String email;

}
