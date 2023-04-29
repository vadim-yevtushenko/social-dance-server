package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.DancerDto;
import com.example.socialdanceserver.model.DancerEntity;

import java.util.List;
import java.util.UUID;

public interface DancerService {

    List<DancerDto> getAll();

    List<DancerEntity> getAllByCity(String city);

    List<DancerEntity> getAllByName(String name);

    List<DancerEntity> getAllBySurname(String surname);

    List<DancerEntity> getAllByNameAndSurname(String name, String surname);

    DancerEntity getById(UUID id);

    DancerDto save(DancerDto dancerDto);

    void deleteById(UUID id);

    Integer checkSignUpByEmail(String email);

    Integer checkSignInByEmailAndPassword(String email, String password);

    Boolean changePassword(String email, String password);

    Boolean changeEmail(String oldEmail, String newEmail);
}
