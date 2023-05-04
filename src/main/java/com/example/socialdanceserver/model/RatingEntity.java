package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class RatingEntity extends AbstractBaseEntity{

    private UUID ratingOwnerID;

    private UUID  baseDanceEntityId;

    private int rating;

}
