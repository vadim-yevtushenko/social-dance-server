package com.example.socialdanceserver.service;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Dancer;

import java.util.List;

public interface DancerService {

    List<AbstractBaseEntity> getAll();

    List<Dancer> getAllByType();

    Dancer getById(int id);

//    Dancer save(Dancer dancer);

//    void update(Dancer dancer);

    DancerTo save(DancerTo dancerTo);

    void deleteById(int id);

    Integer checkSignUpByEmail(String email);

    Integer checkSignIpByEmailAndPassword(String email, String password);
}
