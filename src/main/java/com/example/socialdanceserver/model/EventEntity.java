package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Dance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
public class EventEntity extends AbstractBaseEntity {

    private String name;

    private String description;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntityInfo entityInfo;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_has_dances",
            joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "dance")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dance> dances;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<DancerEntity> owners;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private SchoolEntity eventOwner;

    private ZonedDateTime dateEvent;

    private ZonedDateTime dateFinishEvent;

}
