package com.example.socialdanceserver.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abstract_base_entity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AbstractBaseEntity abstractBaseEntity;
    private int rating;

    public Rating() {
    }

    public Rating(AbstractBaseEntity abstractBaseEntity, int rating) {
        this.abstractBaseEntity = abstractBaseEntity;
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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", abstractBaseEntity=" + abstractBaseEntity.getId() +
                ", rating=" + rating +
                '}';
    }
}
