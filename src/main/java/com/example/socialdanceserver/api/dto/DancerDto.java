package com.example.socialdanceserver.api.dto;

import com.example.socialdanceserver.api.dto.dtocontainer.IdNameContainerDto;
import com.example.socialdanceserver.persistence.entity.enums.Level;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DancerDto extends BaseDto{

    private String name;

    private String lastName;

    private String gender;

    private LocalDate birthday;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private Level level;

    private List<DanceDto> dances;

    private IdNameContainerDto administrator;

    private IdNameContainerDto teacher;

    private IdNameContainerDto school;

//    private CredentialEntity credential;

}
