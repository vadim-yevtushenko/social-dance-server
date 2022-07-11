package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.RatingTo;
import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.repository.SchoolRepository;
import com.example.socialdanceserver.service.SchoolService;
import com.example.socialdanceserver.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<AbstractBaseEntity> getAll() {
        return schoolRepository.findAll();
    }

    @Override
    public List<SchoolEntity> getAllByType() {
        List<SchoolEntity> schoolEntityList = new ArrayList<>(new HashSet<>(schoolRepository.findAllByType()));
        schoolEntityList.sort(Comparator.comparing(s -> s.getEntityInfo().getCity().toLowerCase(Locale.ROOT)));
        return schoolEntityList;
    }

    @Override
    public List<SchoolEntity> getAllByOwnerId(int id) {
        return schoolRepository.findAllByOwnerId(id);
    }

    @Override
    public List<SchoolEntity> getAllByCity(String city) {
        return new ArrayList<>(new HashSet<>(schoolRepository.findAllByCity(city)));
    }

    @Override
    public List<ReviewEntity> getReviewsBySchoolId(int id) {
        return new HashSet<>(getById(id).getReviews())
                .stream().sorted(Comparator.comparing(ReviewEntity::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public SchoolEntity getById(int id) {
        SchoolEntity schoolEntity = null;
        Optional<AbstractBaseEntity> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            schoolEntity = (SchoolEntity) optionalSchool.get();
        }
        return schoolEntity;
    }

    @Override
    public SchoolEntity save(SchoolEntity schoolEntity) {
        return schoolRepository.save(schoolEntity);
    }

    @Override
    public void createRating(RatingTo ratingTo) {
        schoolRepository.createRating(ratingTo.getEntityId(),
                ratingTo.getReviewerId(), ratingTo.getRating());
    }

    @Override
    public void createReview(ReviewTo reviewTo) {
        schoolRepository.createReview(reviewTo.getAbstractBaseEntityId(),
                reviewTo.getSchoolId(), reviewTo.getReview(),
                DateTimeUtils.fromDateToLocalDateTime(reviewTo.getDateTime()),
                reviewTo.getIncognito());
    }

    @Override
    public void saveRating(RatingTo ratingTo) {
        SchoolEntity schoolEntity = getById(ratingTo.getEntityId());
        boolean isExist = false;
        for (RatingEntity ratingEntity : schoolEntity.getRatings()){
            if (ratingEntity.getReviewer_id() == ratingTo.getReviewerId()){
                isExist = true;
                break;
            }
        }
        if (isExist) {
            schoolRepository.saveRating(ratingTo.getReviewerId(), ratingTo.getRating());
        } else {
            System.out.println(ratingTo);
            schoolRepository.createRating(ratingTo.getEntityId(), ratingTo.getReviewerId(), ratingTo.getRating());
        }
    }

    @Override
    public void saveReview(ReviewTo reviewTo) {
        schoolRepository.saveReview(reviewTo.getId(), reviewTo.getReview(),
                DateTimeUtils.fromDateToLocalDateTime(reviewTo.getDateTime()));
    }

    @Override
    public void update(SchoolEntity schoolEntity) {
        schoolRepository.save(schoolEntity);
    }

    @Override
    public void deleteById(int id) {
        schoolRepository.deleteById(id);
    }
}
