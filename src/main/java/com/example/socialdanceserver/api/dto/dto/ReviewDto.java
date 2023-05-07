package com.example.socialdanceserver.api.dto.dto;

import com.example.socialdanceserver.model.DancerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

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