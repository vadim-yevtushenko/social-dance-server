package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Dance;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "entity_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntityInfo entityInfo;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "school_has_dances",
            joinColumns = @JoinColumn(name = "school_id"))
    @Column(name = "dance")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dance> dances;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<DancerEntity> owners;

//    @OneToMany(mappedBy = "baseDanceEntityId", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ReviewEntity> reviews;

}
