package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RatingDto extends BaseDto{

    private UUID dancerId;

    private UUID schoolId;

    private int rating;

}
