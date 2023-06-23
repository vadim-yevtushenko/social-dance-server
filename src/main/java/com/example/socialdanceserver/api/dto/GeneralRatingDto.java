package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GeneralRatingDto {

    private int average;

    private int totalCount;

    private Map<Integer, Integer> counts;

}
