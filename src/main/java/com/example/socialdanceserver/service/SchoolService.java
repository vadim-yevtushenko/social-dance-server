package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.RatingDto;
import com.example.socialdanceserver.dto.ReviewDto;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.ReviewEntity;
import com.example.socialdanceserver.model.SchoolEntity;

import java.util.List;

public interface SchoolService {

    List<AbstractBaseEntity> getAll();

    List<SchoolEntity> getAllByType();

    List<SchoolEntity> getAllByOwnerId(int id);

    List<SchoolEntity> getAllByCity(String city);

    List<ReviewEntity> getReviewsBySchoolId(int id);

    SchoolEntity getById(int id);

    SchoolEntity save(SchoolEntity schoolEntity);

    void createRating(RatingDto ratingDto);

    void createReview(ReviewDto reviewDto);

    void saveRating(RatingDto ratingDto);

    void saveReview(ReviewDto reviewDto);

    void update(SchoolEntity schoolEntity);

    void deleteById(int id);

}
