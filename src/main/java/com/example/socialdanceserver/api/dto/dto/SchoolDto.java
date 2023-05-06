package com.example.socialdanceserver.api.dto.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SchoolDto {

    private UUID id;

    private String name;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private List<DanceDto> dances;

    private List<DancerDto> administrators;

    private List<DancerDto> teachers;

    private List<DancerDto> students;


//    private AverageRatingDto rating;

//    @JsonManagedReference
//    private List<DancerDto> owners;

}
