package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.RatingDto;
import com.example.socialdanceserver.dto.ReviewDto;
import com.example.socialdanceserver.dto.SchoolDto;
import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.repository.SchoolRepository;
import com.example.socialdanceserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SchoolServiceImpl extends BaseService implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<SchoolDto> getAll() {
        return mapper.mapAsList(schoolRepository.findAll(Sort.by("name")), SchoolDto.class);
    }

//    @Override
//    public List<SchoolDto> getAll() {
//        List<SchoolEntity> schoolEntityList = new ArrayList<>(new HashSet<>(schoolRepository.findAll()));
//        schoolEntityList.sort(Comparator.comparing(s -> s.getEntityInfo().getCity().toLowerCase(Locale.ROOT)));
//        return schoolEntityList;
//    }

    @Override
    public List<SchoolDto> getAllByOwnerId(UUID id) {
        return mapper.mapAsList(schoolRepository.findAllByOwnerId(id), SchoolDto.class);
    }

    @Override
    public List<SchoolDto> getAllByCity(String city) {
        return mapper.mapAsList(schoolRepository.findAllByCity(city), SchoolDto.class);
    }

    @Override
    public List<ReviewDto> getReviewsBySchoolId(UUID id) {
        return null;
    }

    @Override
    public SchoolDto getById(UUID id) {
        SchoolEntity schoolEntity = null;
        Optional<SchoolEntity> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            schoolEntity = optionalSchool.get();
        }
        return mapper.map(schoolEntity, SchoolDto.class);
    }

    @Override
    public SchoolDto save(SchoolDto school) {
        SchoolEntity schoolEntity = mapper.map(school, SchoolEntity.class);
        return mapper.map(schoolRepository.save(schoolEntity), SchoolDto.class);
    }

    @Override
    public void createRating(RatingDto ratingDto) {
        schoolRepository.createRating(ratingDto.getBaseDanceEntityId(),
                ratingDto.getRatingOwnerID(), ratingDto.getRating());
    }

    @Override
    public void createReview(ReviewDto reviewDto) {
        schoolRepository.createReview(reviewDto.getBaseDanceEntityId(),
                reviewDto.getBaseDanceEntityId(), reviewDto.getReview(),
                reviewDto.getCreated(),
                reviewDto.isIncognito());
    }

    @Override
    public void saveRating(RatingDto ratingDto) {
//        SchoolEntity schoolEntity = getById(ratingDto.getEntityId());
        boolean isExist = false;
//        for (RatingEntity ratingEntity : schoolEntity.getRatings()){
//            if (ratingEntity.getReviewer_id() == ratingDto.getReviewerId()){
//                isExist = true;
//                break;
//            }
//        }
        if (isExist) {
            schoolRepository.saveRating(ratingDto.getRatingOwnerID(), ratingDto.getRating());
        } else {
            schoolRepository.createRating(ratingDto.getRatingOwnerID(), ratingDto.getRatingOwnerID(), ratingDto.getRating());
        }
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        schoolRepository.saveReview(reviewDto.getId(), reviewDto.getReview(),
                reviewDto.getCreated());
    }

    @Override
    public void update(SchoolDto school) {
        SchoolEntity schoolEntity = mapper.map(school, SchoolEntity.class);
        schoolRepository.save(schoolEntity);
    }

    @Override
    public void deleteById(UUID id) {
        schoolRepository.deleteById(id);
    }
}
