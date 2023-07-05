package com.example.socialdanceserver.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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
@Builder
@AllArgsConstructor
public class EventEntity extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "school_organizer_id")
    private UUID schoolOrganizerId;

    @Column(name = "date_event")
    private ZonedDateTime dateEvent;

    @Column(name = "date_finish_event")
    private ZonedDateTime dateFinishEvent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @ManyToMany()
    @JoinTable(
            name = "events_has_dances",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dance_id") }
    )
    private List<DanceEntity> dances;


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


}
