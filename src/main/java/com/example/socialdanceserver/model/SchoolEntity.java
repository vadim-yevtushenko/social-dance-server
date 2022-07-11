package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.TypeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
public class SchoolEntity extends AbstractBaseEntity{

    private int ownerId;

    @OneToMany(mappedBy = "schoolEntity", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<ReviewEntity> reviewEntities;

    public SchoolEntity() {
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public SchoolEntity(String image, String name, String description, EntityInfo entityInfo, int ownerId) {
        super(image, name, description, entityInfo);
        this.ownerId = ownerId;
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public SchoolEntity(Integer id, String image, String name, String description, int ownerId) {
        super(id, image, name, description);
        this.ownerId = ownerId;
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public List<ReviewEntity> getReviews() {
        return reviewEntities;
    }

    public void setReviews(List<ReviewEntity> reviewEntities) {
        this.reviewEntities = reviewEntities;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "School{" +
                "ownerId=" + ownerId +
                ", reviews=" + reviewEntities +
                "} " + super.toString();
    }
}
