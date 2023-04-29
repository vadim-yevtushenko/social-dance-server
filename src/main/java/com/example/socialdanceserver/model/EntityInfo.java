package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entity_info")
@Getter
@Setter
@NoArgsConstructor
public class EntityInfo extends AbstractBaseEntity{

    private String country;

    private String city;

    private String street;

    private String building;

    private String suites;

    private String phoneNumber;

    private String email;

}
