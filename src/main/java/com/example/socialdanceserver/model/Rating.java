package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abstract_base_entity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private AbstractBaseEntity abstractBaseEntity;

    private int reviewer_id;

    private int rating;

    public Rating() {
    }

    public Rating(AbstractBaseEntity abstractBaseEntity, int reviewer_id, int rating) {
        this.abstractBaseEntity = abstractBaseEntity;
        this.reviewer_id = reviewer_id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AbstractBaseEntity getAbstractBaseEntity() {
        return abstractBaseEntity;
    }

    public void setAbstractBaseEntity(AbstractBaseEntity abstractBaseEntity) {
        this.abstractBaseEntity = abstractBaseEntity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewer_id() {
        return reviewer_id;
    }

    public void setReviewer_id(int reviewer) {
        this.reviewer_id = reviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id && reviewer_id == rating1.reviewer_id && rating == rating1.rating && Objects.equals(abstractBaseEntity, rating1.abstractBaseEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, abstractBaseEntity, reviewer_id, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", abstractBaseEntity=" + abstractBaseEntity.getId() +
                ", reviewer=" + reviewer_id +
                ", rating=" + rating +
                '}';
    }
}
