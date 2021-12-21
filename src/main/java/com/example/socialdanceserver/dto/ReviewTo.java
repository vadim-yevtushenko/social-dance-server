package com.example.socialdanceserver.dto;

import java.util.Date;

public class ReviewTo {

    private int id;

    private int abstractBaseEntityId;

    private int schoolId;

    private String review;

    private Date dateTime;

    public ReviewTo() {
    }

    public ReviewTo(int abstractBaseEntityId, int school, String review, Date dateTime) {
        this.abstractBaseEntityId = abstractBaseEntityId;
        this.schoolId = school;
        this.dateTime = dateTime;
        this.review = review;
    }

    public ReviewTo(int id, int abstractBaseEntityId, int schoolId, String review, Date dateTime) {
        this.id = id;
        this.abstractBaseEntityId = abstractBaseEntityId;
        this.schoolId = schoolId;
        this.review = review;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbstractBaseEntityId() {
        return abstractBaseEntityId;
    }

    public void setAbstractBaseEntityId(int abstractBaseEntityId) {
        this.abstractBaseEntityId = abstractBaseEntityId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                ", abstractBaseEntityId=" + abstractBaseEntityId +
                ", schoolId=" + schoolId +
                ", review='" + review + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
