package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SocialNetworksDto extends BaseDto{

    private String instagram;

    private String facebook;

    private String youtube;

}
