package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Role;
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
    @Column(name = "role")
    private Role role;

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
    @JsonBackReference
    private SchoolEntity schoolAdministrator;

    @ManyToOne
    @JoinTable(
            name = "school_has_teachers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
    @JsonBackReference
    private SchoolEntity schoolTeacher;

    @ManyToMany
    @JoinTable(
            name = "schools_has_students",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
    @JsonBackReference
    private List<SchoolEntity> schoolStudent;

    @ManyToOne
    @JoinTable(
            name = "events_has_organizers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    @JsonBackReference
    private EventEntity eventOrganizer;

    @ManyToMany
    @JoinTable(
            name = "events_has_dancers",
            joinColumns = {@JoinColumn(name = "dancer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    @JsonBackReference
    private List<SchoolEntity> eventParticipant;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "credential_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CredentialEntity credential;

}
