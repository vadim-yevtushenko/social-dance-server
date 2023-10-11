package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactInfoDto extends BaseDto{

    private String country;

    private String city;

    private String address;

    private Double latitude;

    private Double longitude;

    private String phoneNumber;

    private String email;

}
