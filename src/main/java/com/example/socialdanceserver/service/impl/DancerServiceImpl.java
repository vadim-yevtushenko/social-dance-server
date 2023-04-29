package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.DancerDto;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.CredentialEntity;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.mapper.DancerMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public DancerEntity getById(UUID id) {
        DancerEntity dancerEntity = null;
        Optional<DancerEntity> dancerOptional = dancerRepository.findById(id);
        if (dancerOptional.isPresent()){
            dancerEntity = (DancerEntity) dancerOptional.get();
        }
        return dancerEntity;
    }

    @Override
    public DancerDto save(DancerDto dancerDto) {
        DancerEntity oldDancerEntity = new DancerEntity();
//        if (dancerDto.getId() != null){
//            oldDancerEntity = getById(dancerDto.getId());
//        }
//        DancerEntity dancerEntity = DancerMapper.populateDancerEntity(dancerDto, oldDancerEntity);
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setCountry("Ukraine");
        entityInfo.setCity("Zp");
        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setEmail("mail@gmail.com");
        credentialEntity.setPassword("1234");
        DancerEntity dancerEntity = new DancerEntity();
        dancerEntity.setBirthday(LocalDate.now());
        dancerEntity.setName("Vadim");
        dancerEntity.setLastName("Yevt");
        dancerEntity.setDances(Set.of(Dance.SALSA,Dance.BACHATA));
        dancerEntity.setGender("male");
        dancerEntity.setRole(Role.DANCER);
        dancerEntity.setEntityInfo(entityInfo);
        dancerEntity.setCredential(credentialEntity);
//        return DancerMapper.mapDancerEntity(dancerRepository.save(dancerEntity));
        return mapper.map(dancerRepository.save(dancerEntity), DancerDto.class);
    }

    @Override
    public void deleteById(UUID id) {
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
