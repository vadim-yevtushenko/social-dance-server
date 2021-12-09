package com.example.socialdanceserver.service;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Dancer;

import java.util.List;

public interface DancerService {

    List<AbstractBaseEntity> getAll();

    Dancer getById(int id);

    Dancer create(Dancer dancer);

    void update(Dancer dancer);

    void deleteById(int id);
}
