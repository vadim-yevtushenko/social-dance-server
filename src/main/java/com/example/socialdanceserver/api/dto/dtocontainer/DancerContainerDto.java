package com.example.socialdanceserver.api.dto.dtocontainer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DancerContainerDto {

    private UUID id;

    private String name;

    private String lastName;

    private String image;

}
