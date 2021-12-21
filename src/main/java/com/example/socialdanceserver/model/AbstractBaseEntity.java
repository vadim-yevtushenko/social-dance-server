package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.TypeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_base_entity")
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "base_seq", initialValue = 1, allocationSize = 1)
    private Integer id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeEntity typeEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_info_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntityInfo entityInfo;

    @OneToMany(mappedBy = "abstractBaseEntity", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Rating> ratings;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "abstract_base_entity_dances",
            joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "dance")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dance> dances;

    public AbstractBaseEntity() {

    }

    public AbstractBaseEntity(String name, String description, EntityInfo entityInfo) {
        this.name = name;
        this.description = description;
        this.entityInfo = entityInfo;
    }

    public AbstractBaseEntity(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String countRating() {
        if (ratings == null || ratings.size() == 0) {
            return "0";
        }
        int sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return String.format("%.2f / dancers: %d", ((double)sum / ratings.size()), ratings.size());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }


    public Set<Dance> getDances() {
        return dances;
    }

    public void setDances(Set<Dance> dances) {
        this.dances = dances;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", typeEntity=" + typeEntity +
                ", entityInfo=" + entityInfo +
                ", ratings=" + ratings +
                ", dances=" + dances +
                '}';
    }
}
