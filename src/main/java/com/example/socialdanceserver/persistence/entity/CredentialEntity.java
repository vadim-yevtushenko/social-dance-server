package com.example.socialdanceserver.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
@NoArgsConstructor
public class CredentialEntity extends AbstractBaseEntity{

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dancer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DancerEntity dancer;

}
