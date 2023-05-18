package com.example.socialdanceserver.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SchoolDto extends BaseDto{

    private String name;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private List<DanceDto> dances;

    private List<DancerDto> administrators;

    private List<DancerDto> teachers;

    private List<DancerDto> students;

//    private AverageRatingDto rating;

}
