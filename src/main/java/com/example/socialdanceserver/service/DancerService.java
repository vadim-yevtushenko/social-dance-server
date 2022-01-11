package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.Dancer;

import java.util.List;

public interface DancerService {

    List<Dancer> getAllByType();

    List<Dancer> getAllByCity(String city);

    List<Dancer> getAllByName(String name);

    List<Dancer> getAllBySurname(String surname);

    List<Dancer> getAllByNameAndSurname(String name, String surname);

    Dancer getById(int id);

    DancerTo save(DancerTo dancerTo);

    void deleteById(int id);

    Integer checkSignUpByEmail(String email);

    Integer checkSignIpByEmailAndPassword(String email, String password);
}
