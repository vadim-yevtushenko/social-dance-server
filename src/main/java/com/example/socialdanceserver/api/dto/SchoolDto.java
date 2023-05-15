package com.example.socialdanceserver.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SchoolDto extends BaseDto{

    private UUID id;

    private String name;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private List<DanceDto> dances;

    @JsonManagedReference
    private List<DancerDto> administrators;

    @JsonManagedReference
    private List<DancerDto> teachers;

    @JsonIgnoreProperties("schoolStudent")
    private List<DancerDto> students;

//    private AverageRatingDto rating;

}
