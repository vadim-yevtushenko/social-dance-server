package com.example.socialdanceserver.dto;

import java.util.Date;

public class ReviewDto {

    private int id;

    private Boolean incognito;

    private int abstractBaseEntityId;

    private int schoolId;

    private String review;

    private Date dateTime;

    public ReviewDto() {
    }

    public ReviewDto(int id, Boolean incognito, int abstractBaseEntityId, int schoolId, String review, Date dateTime) {
        this.id = id;
        this.incognito = incognito;
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

    public Boolean getIncognito() {
        return incognito;
    }

    public void setIncognito(Boolean incognito) {
        this.incognito = incognito;
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
        return "ReviewTo{" +
                "id=" + id +
                ", incognito=" + incognito +
                ", abstractBaseEntityId=" + abstractBaseEntityId +
                ", schoolId=" + schoolId +
                ", review='" + review + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
