package com.example.socialdanceserver.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ContactInfoEntity extends AbstractBaseEntity{

    private String country;

    private String city;

    private String street;

    private String building;

    private String suites;

    private String phoneNumber;

    private String email;

}
