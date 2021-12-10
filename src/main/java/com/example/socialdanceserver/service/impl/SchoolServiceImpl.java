package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.School;
import com.example.socialdanceserver.repository.SchoolRepository;
import com.example.socialdanceserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<AbstractBaseEntity> getAll() {
        return schoolRepository.findAll();
    }

    @Override
    public List<School> getAllByType() {
        return schoolRepository.findAllByType();
    }

    @Override
    public School getById(int id) {
        School school = null;
        Optional<AbstractBaseEntity> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            school = (School) optionalSchool.get();
        }
        return school;
    }

    @Override
    public School create(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void update(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void deleteById(int id) {
        schoolRepository.deleteById(id);
    }
}
