package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.dto.SchoolTo;
import com.example.socialdanceserver.model.Review;
import com.example.socialdanceserver.model.School;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolUtils {

    public static List<SchoolTo> getSchoolTos(List<School> schoolList){
        return schoolList.stream().map(SchoolUtils::createSchoolTo).
                collect(Collectors.toList());
    }

    public static SchoolTo createSchoolTo(School school){
        return new SchoolTo(school.getId(), school.getName(),
                school.getDescription(), school.getEntityInfo(),
                school.createAverageRating(), school.getOwnerId(),
                new ArrayList<>(school.getDances()));
    }

    public static List<ReviewTo> getReviewTos(List<Review> reviewList){
        return reviewList.stream().map(SchoolUtils::createReviewTo).
                collect(Collectors.toList());
    }

    private static ReviewTo createReviewTo(Review review) {
        return new ReviewTo(review.getId(), review.getIncognito(), review.getAbstractBaseEntityId(),
                review.getSchool().getId(), review.getReview(),
                DateTimeUtils.fromLocalDateTimeToDate(review.getDateTime()));
    }
}
