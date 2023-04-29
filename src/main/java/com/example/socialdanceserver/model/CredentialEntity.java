package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
@NoArgsConstructor
public class CredentialEntity extends AbstractBaseEntity{

    private String email;

    private String password;

}
