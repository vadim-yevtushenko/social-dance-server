package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.api.dto.dtocontainer.DancerContainerDto;
import com.example.socialdanceserver.persistence.dao.ReviewDao;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.entity.RatingEntity;
import com.example.socialdanceserver.persistence.entity.ReviewEntity;
import com.example.socialdanceserver.persistence.repository.DancerRepository;
import com.example.socialdanceserver.persistence.repository.RatingRepository;
import com.example.socialdanceserver.persistence.repository.ReviewRepository;
import com.example.socialdanceserver.service.ReviewService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl extends BaseService implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private DancerRepository dancerRepository;

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public ReviewDto save(ReviewDto review) {
        ReviewEntity reviewEntity = mapper.map(review, ReviewEntity.class);
        RatingEntity ratingEntity = ratingRepository.findRatingEntityByObjectIdAndDancerId(review.getObjectId(), review.getDancerId());
        if (ratingEntity.getId() != null){
            reviewEntity.setRating(ratingEntity);
        }
        return mapper.map(reviewRepository.save(reviewEntity), ReviewDto.class);
    }

    @Override
    public void saveAll(List<ReviewEntity> reviews) {
        reviewRepository.saveAll(reviews);
    }

    @Override
    public PageDto<ReviewDto> getPageReviewsByObjectId(UUID objectId, int pageNumber, int size) {
        Map<String, String> mapPredicates = reviewDao.getMapPredicates(objectId.toString());
        int total = reviewDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("created"), mapPredicates, pageNumber, size, total);
        List<ReviewEntity> reviewEntities = reviewDao.load(paginationRequest);

        return new PageDto<>(total, getReviewDtosFromEntities(reviewEntities));
    }

    @Override
    public List<ReviewEntity> getByObjectIdAndDancerId(UUID objectId, UUID dancerId) {
        return reviewRepository.findReviewEntitiesByObjectIdAndDancerId(objectId, dancerId);
    }

    @Override
    public List<ReviewEntity> getByObjectId(UUID objectId) {
        return reviewRepository.findReviewEntitiesByObjectId(objectId);
    }

    @Override
    public void deleteReviewEntities(List<ReviewEntity> reviewEntities) {
        reviewRepository.deleteAll(reviewEntities);
    }

    private List<ReviewDto> getReviewDtosFromEntities(List<ReviewEntity> reviewEntities) {
        return mapper.mapAsList(reviewEntities, ReviewDto.class)
                .stream()
                .peek(reviewDto -> {
                    DancerEntity dancerEntity = dancerRepository.getById(reviewDto.getDancerId());
                    reviewDto.setDancerContainer(mapper.map(dancerEntity, DancerContainerDto.class));
                })
                .collect(Collectors.toList());
    }

}
