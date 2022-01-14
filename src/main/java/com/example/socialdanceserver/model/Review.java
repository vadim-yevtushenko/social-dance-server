package com.example.socialdanceserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "incognito")
    private boolean isIncognito;

    private int abstractBaseEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private School school;

    private String review;

    private LocalDateTime dateTime;

    public Review() {
    }

    public Review(boolean isIncognito, int abstractBaseEntityId, School school, String review, LocalDateTime dateTime) {
        this.isIncognito = isIncognito;
        this.abstractBaseEntityId = abstractBaseEntityId;
        this.school = school;
        this.review = review;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIncognito() {
        return isIncognito;
    }

    public void setIncognito(boolean incognito) {
        isIncognito = incognito;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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
                ", school=" + school.getName() +
                ", dateTime=" + dateTime +
                ", review='" + review + '\'' +
                '}';
    }
}
