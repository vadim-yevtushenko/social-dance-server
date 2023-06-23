package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.persistence.entity.ReviewEntity;
import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewDto save(ReviewDto review);

    void saveAll(List<ReviewEntity> reviews);

    PageDto<ReviewDto> getPageReviewsBySchoolId(UUID schoolId, int pageNumber, int size);

    List<ReviewEntity> getBySchoolIdAndDancerId(UUID schoolId, UUID dancerId);

}
