package com.example.socialdanceserver.model;

import com.example.socialdanceserver.dto.AverageRatingDto;
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
    @SequenceGenerator(name = "auto_gen", sequenceName = "base_seq", allocationSize = 1)
    private Integer id;
    private String image;
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
    private Set<RatingEntity> ratingEntities;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "abstract_base_entity_dances",
            joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "dance")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dance> dances;

    public AbstractBaseEntity() {

    }

    public AbstractBaseEntity(String image, String name, String description, EntityInfo entityInfo) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.entityInfo = entityInfo;
    }

    public AbstractBaseEntity(Integer id, String image, String name, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public AverageRatingDto createAverageRating() {
        if (ratingEntities == null || ratingEntities.size() == 0) {
            return null;
        }
        int sum = 0;
        for (RatingEntity ratingEntity : ratingEntities) {
            sum += ratingEntity.getRating();
        }
        return new AverageRatingDto((float)sum / ratingEntities.size(), ratingEntities.size());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Set<RatingEntity> getRatings() {
        return ratingEntities;
    }

    public void setRatings(Set<RatingEntity> ratingEntities) {
        this.ratingEntities = ratingEntities;
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
                ", ratings=" + ratingEntities +
                ", dances=" + dances +
                '}';
    }
}
