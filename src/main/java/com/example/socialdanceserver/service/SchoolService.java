package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.RatingTo;
import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Review;
import com.example.socialdanceserver.model.School;

import java.util.List;

public interface SchoolService {

    List<AbstractBaseEntity> getAll();

    List<School> getAllByType();

    List<School> getAllByOwnerId(int id);

    List<Review> getReviewsBySchoolId(int id);

    School getById(int id);

    School save(School school);

    void createRating(RatingTo ratingTo);

    void createReview(ReviewTo reviewTo);

    void saveRating(RatingTo ratingTo);

    void saveReview(ReviewTo reviewTo);

    void update(School school);

    void deleteById(int id);

}
