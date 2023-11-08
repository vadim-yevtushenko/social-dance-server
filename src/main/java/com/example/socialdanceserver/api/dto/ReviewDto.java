package com.example.socialdanceserver.api.dto;

import com.example.socialdanceserver.api.dto.dtocontainer.DancerContainerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto extends BaseDto{

    private boolean incognito;

    private UUID dancerId;

    private UUID objectId;

    private String review;

    private RatingDto rating;

    private ZonedDateTime created;

    private DancerContainerDto dancerContainer;

}
