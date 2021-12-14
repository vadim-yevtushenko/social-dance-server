package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.SchoolTo;
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
                String.valueOf(school.countRating()), school.getOwnerId(),
                new ArrayList<>(school.getDances()));
    }
}
