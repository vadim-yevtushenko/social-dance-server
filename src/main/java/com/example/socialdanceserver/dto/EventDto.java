package com.example.socialdanceserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    private UUID id;

    private String name;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private ZonedDateTime dateEvent;

    private ZonedDateTime dateFinishEvent;

    private ZonedDateTime created;

    private List<DanceDto> dances;

    private List<DancerDto> owners;

    private List<SchoolDto> eventOwner;

}
