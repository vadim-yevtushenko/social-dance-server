package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.DancerDto;
import java.util.List;
import java.util.UUID;

public interface DancerService {

    List<DancerDto> getAll();

    List<DancerDto> getAllByCity(String city);

    List<DancerDto> getAllByName(String name);

    List<DancerDto> getAllBySurname(String surname);

    List<DancerDto> getAllByNameAndSurname(String name, String surname);

    DancerDto getById(UUID id);

    DancerDto save(DancerDto dancerDto);

    void deleteById(UUID id);

    Integer checkSignUpByEmail(String email);

    Integer checkSignInByEmailAndPassword(String email, String password);

    Boolean changePassword(String email, String password);

    Boolean changeEmail(String oldEmail, String newEmail);
}
