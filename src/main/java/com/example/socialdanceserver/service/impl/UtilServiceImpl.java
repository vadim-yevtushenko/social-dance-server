package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DanceDto;
import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;
import com.example.socialdanceserver.persistence.repository.CityRepository;
import com.example.socialdanceserver.persistence.repository.CountryRepository;
import com.example.socialdanceserver.persistence.repository.DancerRepository;
import com.example.socialdanceserver.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilServiceImpl extends BaseService implements UtilService {

    @Autowired
    private DancerRepository dancerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;


    @Override
    public List<DanceDto> getDances() {
        return mapper.mapAsList(dancerRepository.getAllDances(), DanceDto.class);
    }

    @Override
    public List<CountryDto> getCountries() {
        return null;
    }

    @Override
    public List<CityDto> getCities() {
        return null;
    }
}
