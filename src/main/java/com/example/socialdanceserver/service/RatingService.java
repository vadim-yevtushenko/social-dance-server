package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import com.example.socialdanceserver.persistence.entity.RatingEntity;
import java.util.List;
import java.util.UUID;

public interface RatingService {

    RatingDto save(RatingDto rating);

    GeneralRatingDto createGeneralRatingForObject(UUID objectId);

    RatingDto getByObjectIdAndDancerId(UUID objectId, UUID dancerId);

    List<RatingEntity> getByObjectId(UUID objectId);

    void deleteRatings(List<RatingEntity> ratingEntities);

}
