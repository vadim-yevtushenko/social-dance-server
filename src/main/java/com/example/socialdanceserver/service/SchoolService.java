package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import java.util.List;
import java.util.UUID;

public interface SchoolService {

    List<SchoolDto> getAll();

    List<SchoolDto> getAllByName(String name);

    List<SchoolDto> getAllByCity(String city);

    List<ReviewDto> getReviewsBySchoolId(UUID id);

    SchoolDto getById(UUID id);

    SchoolDto save(SchoolDto school);

    void deleteById(UUID id);

//    void createRating(RatingDto rating);
//
//    void createReview(ReviewDto review);
//
//    void saveRating(RatingDto rating);
//
//    void saveReview(ReviewDto review);

//    void update(SchoolDto school);

}
