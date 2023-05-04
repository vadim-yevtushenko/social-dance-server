package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Dance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
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

    @OneToOne
    @JoinColumn(name = "id")
    private SchoolEntity schoolOrganizer;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "events_has_organizers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dancer_id") }
    )
    private List<DancerEntity> organizers;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "events_has_dancers",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "dancer_id") }
    )
    private List<DancerEntity> dancers;

    private ZonedDateTime dateEvent;

    private ZonedDateTime dateFinishEvent;

}
