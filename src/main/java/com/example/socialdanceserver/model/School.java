package com.example.socialdanceserver.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
@Inheritance(strategy = InheritanceType.JOINED)
public class School extends AbstractBaseEntity{


    @OneToMany(mappedBy = "school", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Review> reviews;

    public School() {
    }

    public School(String name, String description, EntityInfo entityInfo) {
        super(name, description, entityInfo);

    }

    public School(Integer id, String name, String description) {
        super(id, name, description);

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
                ", reviews=" + reviews +
                "} " + super.toString();
    }
}
