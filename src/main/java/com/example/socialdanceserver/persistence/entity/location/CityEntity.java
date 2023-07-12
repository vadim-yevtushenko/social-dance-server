package com.example.socialdanceserver.persistence.entity.location;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "city_country_id_fkey"))
    private CountryEntity country;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;
}
