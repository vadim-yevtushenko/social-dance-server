package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.location.CityDto;
import com.example.socialdanceserver.api.dto.location.CountryDto;
import com.example.socialdanceserver.service.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = UtilRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class UtilRestController extends BaseController{

    static final String REST_URL = "/utils";

    @Autowired
    private UtilService utilService;

    @GetMapping("/countries")
    public List<CountryDto> getCountriesByName(@RequestParam(value = "name", required = false) String name) {
        return utilService.getCountriesByName(name);
    }

    @GetMapping("/cities")
    public List<CityDto> getCitiesByNameAndCountry(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "country", required = false) String country) {
        return utilService.getCitiesByNameAndCountry(name, country);
    }
}
