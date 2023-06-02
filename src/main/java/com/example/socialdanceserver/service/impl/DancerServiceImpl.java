package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.dtocontainer.IdNameContainerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.dao.DancerDao;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.repository.DancerRepository;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DancerServiceImpl extends BaseService implements DancerService {

    @Autowired
    private DancerRepository dancerRepository;

    @Autowired
    private DancerDao dancerDao;

    @Override
    public PageDto<DancerDto> getPageDancers(String name, String lastName, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = dancerDao.getMapPredicates(name, lastName, city);
        int total = dancerDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("name", "lastName"), mapPredicates, pageNumber, size, total);

        List<DancerEntity> dancerEntities = dancerDao.load(paginationRequest);

        return new PageDto<>(total, getDancerDtosFromEntities(dancerEntities));
    }

    @Override
    public DancerDto getById(UUID id) {
        Optional<DancerEntity> optionalDancerEntity = dancerRepository.findById(id);
        return optionalDancerEntity.map(dancer -> mapper.map(dancer, DancerDto.class)).orElse(null);
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

    private List<DancerDto> getDancerDtosFromEntities(List<DancerEntity> dancerEntities) {

        List<DancerDto> dancers = mapper.mapAsList(dancerEntities, DancerDto.class)
                .stream()
                .peek(dancerDto -> {
                    DancerEntity dancerEntity = dancerEntities.stream()
                            .filter(dancer -> dancer.getId().equals(dancerDto.getId()))
                            .findAny()
                            .orElse(null);
                    dancerDto.setAdministrator(mapper.map(dancerEntity.getSchoolAdministrator(), IdNameContainerDto.class));
                    dancerDto.setTeacher(mapper.map(dancerEntity.getSchoolTeacher(), IdNameContainerDto.class));
                    dancerDto.setSchool(mapper.map(dancerEntity.getSchoolStudent(), IdNameContainerDto.class));
                })
                .collect(Collectors.toList());

        return dancers;
    }

    private DancerDto getDancerDtoFromEntity(DancerEntity dancerEntity) {

        DancerDto dancerDto = mapper.map(dancerEntity, DancerDto.class);
        dancerDto.setAdministrator(mapper.map(dancerEntity.getSchoolAdministrator(), IdNameContainerDto.class));
        dancerDto.setTeacher(mapper.map(dancerEntity.getSchoolTeacher(), IdNameContainerDto.class));
        dancerDto.setSchool(mapper.map(dancerEntity.getSchoolStudent(), IdNameContainerDto.class));

        return dancerDto;
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
