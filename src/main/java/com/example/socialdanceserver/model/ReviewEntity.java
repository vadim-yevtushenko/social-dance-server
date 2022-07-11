package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private Boolean incognito;

    private int abstractBaseEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private SchoolEntity schoolEntity;

    private String review;

    private LocalDateTime dateTime;

    public ReviewEntity() {
    }

    public ReviewEntity(Boolean incognito, int abstractBaseEntityId, SchoolEntity schoolEntity, String review, LocalDateTime dateTime) {
        this.incognito = incognito;
        this.abstractBaseEntityId = abstractBaseEntityId;
        this.schoolEntity = schoolEntity;
        this.review = review;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIncognito() {
        return incognito;
    }

    public void setIncognito(Boolean incognito) {
        this.incognito = incognito;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String text) {
        this.review = text;
    }

    public int getAbstractBaseEntityId() {
        return abstractBaseEntityId;
    }

    public void setAbstractBaseEntityId(int abstractBaseEntityId) {
        this.abstractBaseEntityId = abstractBaseEntityId;
    }

    public SchoolEntity getSchool() {
        return schoolEntity;
    }

    public void setSchool(SchoolEntity schoolEntity) {
        this.schoolEntity = schoolEntity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", abstractBaseEntityId=" + abstractBaseEntityId +
                ", school=" + schoolEntity.getName() +
                ", dateTime=" + dateTime +
                ", review='" + review + '\'' +
                '}';
    }
}
