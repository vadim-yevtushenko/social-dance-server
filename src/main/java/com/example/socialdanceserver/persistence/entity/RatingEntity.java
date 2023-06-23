package com.example.socialdanceserver.persistence.entity;

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

    @Column(name = "dancer_id")
    private UUID dancerId;

    @Column(name = "school_id")
    private UUID  schoolId;

    @Column(name = "rating")
    private int rating;

}
