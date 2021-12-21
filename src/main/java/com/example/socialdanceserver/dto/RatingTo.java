package com.example.socialdanceserver.dto;


public class RatingTo {

    private int id;

    private int entityId;

    private int reviewerId;

    private int rating;

    public RatingTo() {
    }

    public RatingTo(int entityId, int reviewerId, int rating) {
        this.entityId = entityId;
        this.reviewerId = reviewerId;
        this.rating = rating;
    }

    public RatingTo(int id, int entityId, int reviewerId, int rating) {
        this.id = id;
        this.entityId = entityId;
        this.reviewerId = reviewerId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewer) {
        this.reviewerId = reviewer;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", abstractBaseEntity=" + entityId +
                ", reviewer=" + reviewerId +
                ", rating=" + rating +
                '}';
    }
}
