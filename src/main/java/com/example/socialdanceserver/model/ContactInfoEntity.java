package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
@NoArgsConstructor
public class ContactInfoEntity extends AbstractBaseEntity{

    private String country;

    private String city;

    private String street;

    private String building;

    private String suites;

    private String phoneNumber;

    private String email;

}
