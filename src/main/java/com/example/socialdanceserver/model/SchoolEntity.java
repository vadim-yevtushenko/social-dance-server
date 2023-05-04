package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
public class SchoolEntity extends AbstractBaseEntity {

    private String name;

    private String description;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "schools_has_dances",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dance_id")}
    )
    private List<DanceEntity> dances;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "school_has_administrators",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
    private List<DancerEntity> administrators;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "school_has_teachers",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
    private List<DancerEntity> teachers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "schools_has_students",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
//    @ElementCollection
//    @CollectionTable(name = "schools_has_students", joinColumns = @JoinColumn(name = "school_id"))
//    @Column(name = "dancer_id")
    private List<DancerEntity> students;

//    @OneToMany(mappedBy = "baseDanceEntityId", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ReviewEntity> reviews;

}
