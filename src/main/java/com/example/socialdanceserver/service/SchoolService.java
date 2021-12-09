package com.example.socialdanceserver.service;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.School;

import java.util.List;

public interface SchoolService {

    List<AbstractBaseEntity> getAll();

    School getById(int id);

    School create(School school);

    void update(School school);

    void deleteById(int id);

}
