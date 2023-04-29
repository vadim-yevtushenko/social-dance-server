package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.ReviewDto;
import com.example.socialdanceserver.dto.SchoolDto;
import com.example.socialdanceserver.model.ReviewEntity;
import com.example.socialdanceserver.model.SchoolEntity;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolMapper {

    public static List<SchoolDto> mapSchoolTos(List<SchoolEntity> schoolEntityList){
        return schoolEntityList.stream().map(SchoolMapper::mapSchoolTo).
                collect(Collectors.toList());
    }

    public static SchoolDto mapSchoolTo(SchoolEntity schoolEntity){
        return null;
    }

    public static List<ReviewDto> mapReviewTos(List<ReviewEntity> reviewEntityList){
        return reviewEntityList.stream().map(SchoolMapper::mapReviewTo).
                collect(Collectors.toList());
    }

    private static ReviewDto mapReviewTo(ReviewEntity reviewEntity) {
        return null;
    }
}
