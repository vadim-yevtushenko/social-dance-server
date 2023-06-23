package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import java.util.UUID;

public interface RatingService {

    RatingDto save(RatingDto rating);

    GeneralRatingDto createGeneralRatingForSchool(UUID schoolId);

    RatingDto getBySchoolIdAndDancerId(UUID schoolId, UUID dancerId);

}
