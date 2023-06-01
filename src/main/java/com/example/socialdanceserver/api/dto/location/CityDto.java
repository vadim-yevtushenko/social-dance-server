package com.example.socialdanceserver.api.dto.location;

import com.example.socialdanceserver.persistence.entity.enums.CityStatus;
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

    private CityStatus status;

}
