package com.example.socialdanceserver.persistence.entity.location;

import com.example.socialdanceserver.persistence.entity.enums.CityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CityStatus status;
}
