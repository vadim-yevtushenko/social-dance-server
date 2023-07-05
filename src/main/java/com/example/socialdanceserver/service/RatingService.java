package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import com.example.socialdanceserver.persistence.entity.RatingEntity;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    RatingDto save(RatingDto rating);

    GeneralRatingDto createGeneralRatingForSchool(UUID schoolId);

    RatingDto getBySchoolIdAndDancerId(UUID schoolId, UUID dancerId);

    List<RatingEntity> getBySchoolId(UUID schoolId);

    void deleteRatings(List<RatingEntity> ratingEntities);

}
