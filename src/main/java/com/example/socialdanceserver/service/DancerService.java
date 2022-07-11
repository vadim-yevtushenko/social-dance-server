package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.DancerEntity;

import java.util.List;

public interface DancerService {

    List<DancerEntity> getAllByType();

    List<DancerEntity> getAllByCity(String city);

    List<DancerEntity> getAllByName(String name);

    List<DancerEntity> getAllBySurname(String surname);

    List<DancerEntity> getAllByNameAndSurname(String name, String surname);

    DancerEntity getById(int id);

    DancerTo save(DancerTo dancerTo);

    void deleteById(int id);

    Integer checkSignUpByEmail(String email);

    Integer checkSignInByEmailAndPassword(String email, String password);

    Boolean changePassword(String email, String password);

    Boolean changeEmail(String oldEmail, String newEmail);
}
