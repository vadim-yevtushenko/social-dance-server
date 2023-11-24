package com.example.socialdanceserver.api.dto;

import com.example.socialdanceserver.api.dto.dtocontainer.IdNameContainerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDto extends BaseDto{

    private String name;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private SocialNetworksDto socialNetworks;

    private LocalDateTime dateEvent;

    private LocalDateTime dateFinishEvent;

    private ZonedDateTime created;

    private IdNameContainerDto schoolOrganizer;

    private List<DancerDto> organizers;

    private List<DanceDto> dances;

    private List<DancerDto> dancers;

    private GeneralRatingDto generalRating;

}
