package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntityInfo entityInfo;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "dancer_has_dances",
            joinColumns = @JoinColumn(name = "dancer_id"))
    @Column(name = "dance")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dance> dances;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CredentialEntity credential;

}
