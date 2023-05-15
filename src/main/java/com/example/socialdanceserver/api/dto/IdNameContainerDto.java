package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class IdNameContainerDto {

    private UUID id;

    private String name;

}
