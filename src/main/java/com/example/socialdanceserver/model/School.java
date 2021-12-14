package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.TypeEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
public class School extends AbstractBaseEntity{

    private int ownerId;

    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Review> reviews;

    public School() {
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public School(String name, String description, EntityInfo entityInfo, int ownerId) {
        super(name, description, entityInfo);
        this.ownerId = ownerId;
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public School(Integer id, String name, String description, int ownerId) {
        super(id, name, description);
        this.ownerId = ownerId;
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
                ", reviews=" + reviews +
                "} " + super.toString();
    }
}
