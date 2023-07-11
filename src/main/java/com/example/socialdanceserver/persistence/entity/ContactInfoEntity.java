package com.example.socialdanceserver.persistence.entity;

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

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

}
