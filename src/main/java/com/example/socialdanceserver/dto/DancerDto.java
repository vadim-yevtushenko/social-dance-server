package com.example.socialdanceserver.dto;

import com.example.socialdanceserver.model.CredentialEntity;
import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.enums.Dance;
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

    private String image;

    private String name;

    private String lastName;

    private String gender;

    private LocalDate birthday;

    private EntityInfo entityInfo;

    private Role role;

    private List<Dance> dances;

    private CredentialEntity credential;

    private String description;

    public DancerDto(UUID id, String image, String name, String lastName, String gender,
                     LocalDate birthday, EntityInfo entityInfo, Role role,
                     List<Dance> dances, String description, CredentialEntity credential) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.entityInfo = entityInfo;
        this.role = role;
        this.dances = dances;
        this.description = description;
        this.credential = credential;
    }

}
