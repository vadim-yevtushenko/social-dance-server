package com.example.socialdanceserver.api.dto.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto {

    private int id;

    private String name;

    private String iso2;

    private String iso3;

}
