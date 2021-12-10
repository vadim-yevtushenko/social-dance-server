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


    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Review> reviews;

    public School() {
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public School(String name, String description, EntityInfo entityInfo) {
        super(name, description, entityInfo);
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public School(Integer id, String name, String description) {
        super(id, name, description);
        this.setTypeEntity(TypeEntity.SCHOOL);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "School{" +
                "reviews=" + reviews +
                "} " + super.toString();
    }
}
