package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.dto.SchoolTo;
import com.example.socialdanceserver.model.ReviewEntity;
import com.example.socialdanceserver.model.SchoolEntity;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolMapper {

    public static List<SchoolTo> mapSchoolTos(List<SchoolEntity> schoolEntityList){
        return schoolEntityList.stream().map(SchoolMapper::mapSchoolTo).
                collect(Collectors.toList());
    }

    public static SchoolTo mapSchoolTo(SchoolEntity schoolEntity){
        return new SchoolTo(schoolEntity.getId(), schoolEntity.getImage(), schoolEntity.getName(),
                schoolEntity.getDescription(), schoolEntity.getEntityInfo(),
                schoolEntity.createAverageRating(), schoolEntity.getOwnerId(),
                new ArrayList<>(schoolEntity.getDances()));
    }

    public static List<ReviewTo> mapReviewTos(List<ReviewEntity> reviewEntityList){
        return reviewEntityList.stream().map(SchoolMapper::mapReviewTo).
                collect(Collectors.toList());
    }

    private static ReviewTo mapReviewTo(ReviewEntity reviewEntity) {
        return new ReviewTo(reviewEntity.getId(), reviewEntity.getIncognito(), reviewEntity.getAbstractBaseEntityId(),
                reviewEntity.getSchool().getId(), reviewEntity.getReview(),
                DateTimeUtils.fromLocalDateTimeToDate(reviewEntity.getDateTime()));
    }
}
