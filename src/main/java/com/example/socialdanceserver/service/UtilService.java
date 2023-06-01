package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DanceDto;
import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;

import java.util.List;

public interface UtilService {

    List<DanceDto> getDances();

    List<CountryDto> getCountries();

    List<CityDto> getCities();

}