package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dancer")
@Getter
@Setter
@NoArgsConstructor
public class DancerEntity extends AbstractBaseEntity {

    private String name;

    private String lastName;

    private String gender;

    private LocalDate birthday;

    private String description;

    private String image;

    @Enumerated(EnumType.STRING)
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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "credential_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CredentialEntity credential;

}
