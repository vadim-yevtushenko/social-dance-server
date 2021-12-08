package com.example.socialdanceserver.model;

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

    private int abstractBaseEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private School school;

    private String review;

    private LocalDateTime dateTime;

    public Review() {
    }

    public Review(int abstractBaseEntityId, School school, String review, LocalDateTime dateTime) {
        this.abstractBaseEntityId = abstractBaseEntityId;
        this.school = school;
        this.dateTime = dateTime;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
