package com.example.socialdanceserver.persistence.entity;

import com.example.socialdanceserver.persistence.entity.enums.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dancer")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DancerEntity extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "dancers_has_dances",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "dance_id")}
    )
    private List<DanceEntity> dances;

    @ManyToOne
    @JoinTable(
            name = "school_has_administrators",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
//    @JsonBackReference
    private SchoolEntity schoolAdministrator;

    @ManyToOne
    @JoinTable(
            name = "school_has_teachers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
//    @JsonBackReference
    private SchoolEntity schoolTeacher;

    @ManyToOne
    @JoinTable(
            name = "schools_has_students",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
//    @JsonBackReference
    private SchoolEntity schoolStudent;

    @ManyToMany
    @JoinTable(
            name = "events_has_organizers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    @JsonBackReference
    private List<EventEntity> eventOrganizer;

    @ManyToMany
    @JoinTable(
            name = "events_has_dancers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    @JsonBackReference
    private List<EventEntity> eventParticipant;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "credential_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CredentialEntity credential;

}
