package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

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
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @ManyToMany()
    @JoinTable(
            name = "events_has_dances",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dance_id") }
    )
    private List<DanceEntity> dances;

    private UUID schoolOrganizerId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "events_has_organizers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dancer_id") }
    )
    @JsonManagedReference
    private List<DancerEntity> organizers;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "events_has_dancers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dancer_id") }
    )
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<DancerEntity> dancers;

    private ZonedDateTime dateEvent;

    private ZonedDateTime dateFinishEvent;

}
