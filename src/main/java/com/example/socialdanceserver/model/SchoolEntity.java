package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SchoolEntity extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfoEntity contactInfo;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "schools_has_dances",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dance_id")}
    )
    private List<DanceEntity> dances;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "school_has_administrators",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
    @JsonManagedReference
    private List<DancerEntity> administrators;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "school_has_teachers",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
    private List<DancerEntity> teachers;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "schools_has_students",
            joinColumns = {@JoinColumn(name = "school_id")},
            inverseJoinColumns = {@JoinColumn(name = "dancer_id")}
    )
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<DancerEntity> students;

//    @OneToMany(mappedBy = "baseDanceEntityId", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ReviewEntity> reviews;

}
