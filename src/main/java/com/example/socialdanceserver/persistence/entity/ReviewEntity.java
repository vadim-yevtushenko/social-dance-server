package com.example.socialdanceserver.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class ReviewEntity extends AbstractBaseEntity{

    @Column(name = "incognito")
    private boolean incognito;

    @Column(name = "dancer_id")
    private UUID dancerId;

    @Column(name = "school_id")
    private UUID schoolId;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "rating_id")
    private RatingEntity rating;

    @Column(name = "review")
    private String review;

}
