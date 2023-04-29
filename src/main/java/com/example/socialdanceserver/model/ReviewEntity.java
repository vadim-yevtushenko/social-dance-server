package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class ReviewEntity extends AbstractBaseEntity{

    private boolean incognito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_owner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private DancerEntity reviewOwner;

    private UUID baseDanceEntityId;

    private String review;

}
