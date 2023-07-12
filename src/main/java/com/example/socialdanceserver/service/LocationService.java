package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;

import java.util.List;

public interface LocationService {

    List<CountryDto> getCountriesByName(String name);

    List<CityDto> getCitiesByNameAndCountry(String name, String countryName);

}
