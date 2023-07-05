package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import com.example.socialdanceserver.persistence.entity.RatingEntity;
import com.example.socialdanceserver.persistence.entity.ReviewEntity;
import com.example.socialdanceserver.persistence.repository.RatingRepository;
import com.example.socialdanceserver.service.RatingService;
import com.example.socialdanceserver.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RatingServiceImpl extends BaseService implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ReviewService reviewService;

    @Override
    public RatingDto save(RatingDto rating) {
        RatingEntity ratingEntity = ratingRepository.save(mapper.map(rating, RatingEntity.class));

        if (rating.getId() == null){
            List<ReviewEntity> reviewEntities = reviewService.getBySchoolIdAndDancerId(rating.getSchoolId(), rating.getDancerId());
            reviewEntities.forEach(reviewEntity -> reviewEntity.setRating(ratingEntity));
            reviewService.saveAll(reviewEntities);
        }

        return mapper.map(ratingEntity, RatingDto.class);
    }

    @Override
    public RatingDto getBySchoolIdAndDancerId(UUID schoolId, UUID dancerId) {
        RatingDto ratingDto = mapper.map(ratingRepository.findRatingEntityBySchoolIdAndDancerId(schoolId, dancerId), RatingDto.class);
        if (ratingDto == null){
            return new RatingDto();
        }
        return ratingDto;
    }

    @Override
    public List<RatingEntity> getBySchoolId(UUID schoolId) {
        return ratingRepository.findRatingEntitiesBySchoolId(schoolId);
    }

    @Override
    public void deleteRatings(List<RatingEntity> ratingEntities) {
        ratingRepository.deleteAll(ratingEntities);
    }

    @Override
    public GeneralRatingDto createGeneralRatingForSchool(UUID schoolId) {
        List<RatingEntity> ratingEntities = ratingRepository.findRatingEntitiesBySchoolId(schoolId);

        GeneralRatingDto generalRating = new GeneralRatingDto();
        generalRating.setAverage(calcAverageRating(ratingEntities));
        generalRating.setTotalCount(ratingEntities.size());
        generalRating.setCounts(createCounts(ratingEntities));

        return generalRating;
    }

    private int calcAverageRating(List<RatingEntity> ratingEntities) {
        int average = ratingEntities.stream().mapToInt(RatingEntity::getRating).sum();
        return Math.round((float) average / ratingEntities.size());
    }

    private Map<Integer, Integer> createCounts(List<RatingEntity> ratingEntities) {
        int rating1 = 0;
        int rating2 = 0;
        int rating3 = 0;
        int rating4 = 0;
        int rating5 = 0;

        for (RatingEntity rating : ratingEntities) {
            switch (rating.getRating()){
                case 1: {
                    rating1++;
                    break;
                }
                case 2: {
                    rating2++;
                    break;
                }
                case 3: {
                    rating3++;
                    break;
                }
                case 4: {
                    rating4++;
                    break;
                }
                case 5: {
                    rating5++;
                    break;
                }
                default:
            }
        }

        return Map.of(1, rating1, 2, rating2, 3, rating3, 4, rating4, 5, rating5);
    }

}
