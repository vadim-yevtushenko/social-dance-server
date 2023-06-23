package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import com.example.socialdanceserver.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = RatingRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class RatingRestController {

    static final String REST_URL = "/ratings";

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{schoolId}")
    public GeneralRatingDto getGeneralRating(@PathVariable UUID schoolId) {
        return ratingService.createGeneralRatingForSchool(schoolId);
    }

    @GetMapping
    public RatingDto getRating(@RequestParam(value = "schoolId") UUID schoolId, @RequestParam(value = "dancerId") UUID dancerId) {
        return ratingService.getBySchoolIdAndDancerId(schoolId, dancerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RatingDto save(@RequestBody RatingDto ratingDto) {
        return ratingService.save(ratingDto);
    }
}
