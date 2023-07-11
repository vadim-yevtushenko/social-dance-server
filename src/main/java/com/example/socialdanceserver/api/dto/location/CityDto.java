package com.example.socialdanceserver.api.dto.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDto {

    private int id;

    private String name;

    private CountryDto country;

    private String adminName;

    private double lat;

    private double lng;

}
