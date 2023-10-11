package com.example.socialdanceserver.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "social_networks")
@Getter
@Setter
@NoArgsConstructor
public class SocialNetworks extends AbstractBaseEntity{

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "youtube")
    private String youtube;

}
