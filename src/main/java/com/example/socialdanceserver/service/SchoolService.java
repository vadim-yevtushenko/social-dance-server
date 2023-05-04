package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.RatingDto;
import com.example.socialdanceserver.dto.ReviewDto;
import com.example.socialdanceserver.dto.SchoolDto;
import java.util.List;
import java.util.UUID;

public interface SchoolService {

    List<SchoolDto> getAll();

    List<SchoolDto> getAllByOwnerId(UUID id);

    List<SchoolDto> getAllByCity(String city);

    List<ReviewDto> getReviewsBySchoolId(UUID id);

    SchoolDto getById(UUID id);

    SchoolDto save(SchoolDto school);

    void createRating(RatingDto rating);

    void createReview(ReviewDto review);

    void saveRating(RatingDto rating);

    void saveReview(ReviewDto review);

    void update(SchoolDto school);

    void deleteById(UUID id);

}
