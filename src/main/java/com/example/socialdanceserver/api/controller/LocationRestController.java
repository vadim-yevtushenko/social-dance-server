package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;
import com.example.socialdanceserver.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = LocationRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class LocationRestController extends BaseController{

    static final String REST_URL = "/location";

    @Autowired
    private LocationService locationService;

    @GetMapping("/countries")
    public List<CountryDto> getCountriesByName(@RequestParam(value = "name", required = false) String name) {
        return locationService.getCountriesByName(name);
    }

    @GetMapping("/cities")
    public List<CityDto> getCitiesByNameAndCountry(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "country", required = false) String country) {
        return locationService.getCitiesByNameAndCountry(name, country);
    }
}
