package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AverageRatingDto {
    private float averageRating;

    private int numberOfRatings;

    public AverageRatingDto(float averageRating, int numberOfRatings) {
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
    }

}
