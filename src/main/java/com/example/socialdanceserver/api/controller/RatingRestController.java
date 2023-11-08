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
public class RatingRestController extends BaseController{

    static final String REST_URL = "/ratings";

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{objectId}")
    public GeneralRatingDto getGeneralRating(@PathVariable UUID objectId) {
        return ratingService.createGeneralRatingForObject(objectId);
    }

    @GetMapping
    public RatingDto getRating(@RequestParam(value = "objectId") UUID objectId, @RequestParam(value = "dancerId") UUID dancerId) {
        return ratingService.getByObjectIdAndDancerId(objectId, dancerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RatingDto save(@RequestBody RatingDto ratingDto) {
        return ratingService.save(ratingDto);
    }
}
