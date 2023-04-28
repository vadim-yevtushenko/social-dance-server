package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.DancerDto;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.mapper.DancerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DancerServiceImpl implements DancerService {

    @Autowired
    private DancerRepository dancerRepository;

    @Override
    public List<DancerEntity> getAllByType() {
        return dancerRepository.findAllByType();
    }

    @Override
    public List<DancerEntity> getAllByCity(String city) {
        return dancerRepository.findAllByCity(city);
    }

    @Override
    public List<DancerEntity> getAllByName(String name) {
        return dancerRepository.findAllByName(name);
    }

    @Override
    public List<DancerEntity> getAllBySurname(String surname) {
        return dancerRepository.findAllBySurname(surname);
    }

    @Override
    public List<DancerEntity> getAllByNameAndSurname(String name, String surname) {
        return dancerRepository.findAllByNameAndSurname(name, surname);
    }

    @Override
    public DancerEntity getById(int id) {
        DancerEntity dancerEntity = null;
        Optional<AbstractBaseEntity> dancerOptional = dancerRepository.findById(id);
        if (dancerOptional.isPresent()){
            dancerEntity = (DancerEntity) dancerOptional.get();
        }
        return dancerEntity;
    }

    @Override
    public DancerDto save(DancerDto dancerDto) {
        DancerEntity oldDancerEntity = new DancerEntity();
        if (dancerDto.getId() != null){
            oldDancerEntity = getById(dancerDto.getId());
        }
        DancerEntity dancerEntity = DancerMapper.populateDancerEntity(dancerDto, oldDancerEntity);
        return DancerMapper.mapDancerEntity(dancerRepository.save(dancerEntity));
    }

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
    public Integer checkSignInByEmailAndPassword(String email, String password) {
        Integer check = dancerRepository.checkSignInByEmailAndPassword(email, password);
        if (check == null){
            return 0;
        }
        return check;
    }

    @Override
    public Boolean changePassword(String email, String password) {
        dancerRepository.changePassword(email, password);
        return true;
    }

    @Override
    public Boolean changeEmail(String oldEmail, String newEmail) {
        Integer checkEmail = dancerRepository.checkSignUpByEmail(newEmail);
        System.out.println("checkEmail " + checkEmail);
        if (checkEmail == null){
            dancerRepository.changeEmail(oldEmail, newEmail);
            return true;
        }
        return false;
    }
}
