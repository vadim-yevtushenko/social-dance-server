package com.example.socialdanceserver.api.dto;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
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

    private DancerEntity reviewOwner;

    private UUID baseDanceEntityId;

    private String review;

    private ZonedDateTime created;

}
