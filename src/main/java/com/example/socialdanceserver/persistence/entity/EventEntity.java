package com.example.socialdanceserver.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "school_organizer_id")
    private SchoolEntity schoolOrganizer;

    @Column(name = "date_event")
    private LocalDateTime dateEvent;

    @Column(name = "date_finish_event")
    private LocalDateTime dateFinishEvent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "social_networks_id")
    private SocialNetworks socialNetworks;

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
