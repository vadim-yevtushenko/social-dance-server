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

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

}
