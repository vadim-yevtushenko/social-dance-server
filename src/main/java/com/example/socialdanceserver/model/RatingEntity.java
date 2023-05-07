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

//    @Column(name = "ratingOwnerID")
    private UUID ratingOwnerID;

//    @Column(name = "baseDanceEntityId")
    private UUID  baseDanceEntityId;

    @Column(name = "rating")
    private int rating;

}
