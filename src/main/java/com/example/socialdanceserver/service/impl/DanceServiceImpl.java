package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.util.DancerUtils;
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
    public List<Dancer> getAllByType() {
        return dancerRepository.findAllByType();
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
    public DancerTo save(DancerTo dancerTo) {
        Dancer oldDancer = null;
        if (dancerTo.getId() != null){
            oldDancer = getById(dancerTo.getId());
        }
        Dancer dancer = DancerUtils.fromDancerTo(dancerTo, oldDancer);
        return DancerUtils.createDancerTo(dancerRepository.save(dancer));
    }

//    @Override
//    public void update(Dancer dancer) {
//        dancerRepository.save(dancer);
//    }

    @Override
    public void deleteById(int id) {
        dancerRepository.deleteById(id);
    }

    @Override
    public Integer checkSignUpByEmail(String email) {
        Integer check = dancerRepository.checkSignUpByEmail(email);
        if (check == null){
            return 0;
        }
        return check;
    }

    @Override
    public Integer checkSignIpByEmailAndPassword(String email, String password) {
        Integer check = dancerRepository.checkSignInByEmailAndPassword(email, password);
        if (check == null){
            return 0;
        }
        return check;
    }
}