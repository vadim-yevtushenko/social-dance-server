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

    private UUID id;

    private boolean incognito;

    private DancerEntity reviewOwner;

    private UUID baseDanceEntityId;

    private String review;

    private ZonedDateTime created;

    public ReviewDto(UUID id, Boolean incognito, DancerEntity reviewOwner, UUID baseDanceEntityId, String review, ZonedDateTime created) {
        this.id = id;
        this.incognito = incognito;
        this.reviewOwner = reviewOwner;
        this.baseDanceEntityId = baseDanceEntityId;
        this.review = review;
        this.created = created;
    }

}
