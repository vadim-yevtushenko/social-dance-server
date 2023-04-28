package com.example.socialdanceserver.dto;

public class AverageRatingDto {
    private float averageRating;
    private int numberOfRatings;

    public AverageRatingDto(float averageRating, int numberOfRatings) {
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
