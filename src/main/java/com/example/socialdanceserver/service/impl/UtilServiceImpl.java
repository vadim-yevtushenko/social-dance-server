package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;
import com.example.socialdanceserver.persistence.repository.CityRepository;
import com.example.socialdanceserver.persistence.repository.CountryRepository;
import com.example.socialdanceserver.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilServiceImpl extends BaseService implements UtilService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CountryDto> getCountriesByName(String name) {
        return mapper.mapAsList(countryRepository.findByNameStartingWithIgnoreCaseOrderByName(name), CountryDto.class);
    }

    @Override
    public List<CityDto> getCitiesByNameAndCountry(String name, String countryName) {
        return mapper.mapAsList(cityRepository.findByNameStartingWithIgnoreCaseAndCountry_NameStartingWithIgnoreCaseOrderByName(name, countryName), CityDto.class);
    }
}
