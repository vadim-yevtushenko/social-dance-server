package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanceServiceImpl implements DancerService {

    @Autowired
    private DancerRepository dancerRepository;

    @Override
    public List<AbstractBaseEntity> getAll() {
        return dancerRepository.findAll();
    }

    @Override
    public Dancer getById(int id) {
        Dancer dancer = null;
        Optional<AbstractBaseEntity> dancerOptional = dancerRepository.findById(id);
        if (dancerOptional.isPresent()){
            dancer = (Dancer) dancerOptional.get();
        }
        return dancer;
    }

    @Override
    public Dancer create(Dancer dancer) {
        return dancerRepository.save(dancer);
    }

    @Override
    public void update(Dancer dancer) {
        dancerRepository.save(dancer);
    }

    @Override
    public void deleteById(int id) {
        dancerRepository.deleteById(id);
    }
}
