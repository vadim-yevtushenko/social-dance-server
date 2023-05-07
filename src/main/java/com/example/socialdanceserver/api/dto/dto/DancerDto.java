package com.example.socialdanceserver.api.dto.dto;

import com.example.socialdanceserver.model.enums.Role;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DancerDto {

    private UUID id;

    private String name;

    private String lastName;

    private String gender;

    private LocalDate birthday;

    private String description;

    private String image;

    private ContactInfoDto contactInfo;

    private Role role;

    private List<DanceDto> dances;

//    private CredentialEntity credential;

}
