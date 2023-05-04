package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.DancerDto;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DancerServiceImpl extends BaseService implements DancerService {

    @Autowired
    private DancerRepository dancerRepository;

    @Override
    public List<DancerDto> getAll() {
        return mapper.mapAsList(dancerRepository.findAll(Sort.by("name")), DancerDto.class);
    }

    @Override
    public List<DancerDto> getAllByCity(String city) {
        return mapper.mapAsList(dancerRepository.findByContactInfo_CityContainsIgnoreCaseOrderByNameAscLastNameAsc(city), DancerDto.class);
    }

    @Override
    public List<DancerDto> getAllByName(String name) {
        return mapper.mapAsList(dancerRepository.findByNameStartingWithIgnoreCaseOrderByLastName(name), DancerDto.class);
    }

    @Override
    public List<DancerDto> getAllByLastName(String lastName) {
        return mapper.mapAsList(dancerRepository.findByLastNameStartingWithIgnoreCaseOrderByName(lastName), DancerDto.class);
    }

    @Override
    public List<DancerDto> getAllByNameAndLastName(String name, String lastName) {
        return mapper.mapAsList(dancerRepository.findByNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase(name, lastName), DancerDto.class);
    }

    @Override
    public DancerDto getById(UUID id) {
        DancerEntity dancerEntity = null;
        Optional<DancerEntity> dancerOptional = dancerRepository.findById(id);
        if (dancerOptional.isPresent()){
            dancerEntity = dancerOptional.get();
        }
        return mapper.map(dancerEntity, DancerDto.class);
    }

    @Override
    public DancerDto save(DancerDto dancerDto) {
        DancerEntity oldDancerEntity = new DancerEntity();
        DancerEntity dancerEntity = mapper.map(dancerDto, DancerEntity.class);
//        if (dancerDto.getId() != null){
//            oldDancerEntity = getById(dancerDto.getId());
//        }

        return mapper.map(dancerRepository.save(dancerEntity), DancerDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        dancerRepository.deleteById(id);
    }

//    @Override
//    public Integer checkSignUpByEmail(String email) {
//        Integer check = dancerRepository.checkSignUpByEmail(email);
//        if (check == null){
//            return 0;
//        }
//        return check;
//    }
//
//    @Override
//    public Integer checkSignInByEmailAndPassword(String email, String password) {
//        Integer check = dancerRepository.checkSignInByEmailAndPassword(email, password);
//        if (check == null){
//            return 0;
//        }
//        return check;
//    }
//
//    @Override
//    public Boolean changePassword(String email, String password) {
//        dancerRepository.changePassword(email, password);
//        return true;
//    }
//
//    @Override
//    public Boolean changeEmail(String oldEmail, String newEmail) {
//        Integer checkEmail = dancerRepository.checkSignUpByEmail(newEmail);
//        System.out.println("checkEmail " + checkEmail);
//        if (checkEmail == null){
//            dancerRepository.changeEmail(oldEmail, newEmail);
//            return true;
//        }
//        return false;
//    }
}
