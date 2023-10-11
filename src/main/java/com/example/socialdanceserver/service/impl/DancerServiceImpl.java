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
    public PageDto<DancerDto> getPageDancers(String name, String lastName, String country, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = dancerDao.getMapPredicates(name, lastName, country, city);
        int total = dancerDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("name", "lastName"), mapPredicates, pageNumber, size, total);

        List<DancerEntity> dancerEntities = dancerDao.load(paginationRequest);

        return new PageDto<>(total, getDancerDtosFromEntities(dancerEntities));
    }

    @Override
    public DancerDto getById(UUID id) {
        Optional<DancerEntity> optionalDancerEntity = dancerRepository.findById(id);
        return optionalDancerEntity.map(this::getDancerDtoFromEntity).orElse(null);
    }

    @Override
    public DancerDto save(DancerDto dancerDto) {
        DancerEntity dancerEntity = mapper.map(dancerDto, DancerEntity.class);

        if (dancerDto.getId() != null){
            DancerEntity oldDancerEntity = dancerRepository.findById(dancerDto.getId()).orElse(null);
            getDancerEntityFromDto(dancerEntity, oldDancerEntity);
        }

        return mapper.map(dancerRepository.save(dancerEntity), DancerDto.class);
    }

    private DancerEntity getDancerEntityFromDto(DancerEntity dancerEntity, DancerEntity oldDancerEntity) {

        dancerEntity.setEventsOrganizer(oldDancerEntity.getEventsOrganizer());
        dancerEntity.setSchoolAdministrator(oldDancerEntity.getSchoolAdministrator());
        dancerEntity.setSchoolTeacher(oldDancerEntity.getSchoolTeacher());
        dancerEntity.setSchoolStudent(oldDancerEntity.getSchoolStudent());
        dancerEntity.setEventParticipant(oldDancerEntity.getEventParticipant());

        return dancerEntity;
    }

    @Override
    public void deleteById(UUID id) {
        dancerRepository.deleteById(id);
    }

    private List<DancerDto> getDancerDtosFromEntities(List<DancerEntity> dancerEntities) {

        return mapper.mapAsList(dancerEntities, DancerDto.class)
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
    }

    public DancerDto getDancerDtoFromEntity(DancerEntity dancerEntity) {

        DancerDto dancerDto = mapper.map(dancerEntity, DancerDto.class);
        dancerDto.setAdministrator(mapper.map(dancerEntity.getSchoolAdministrator(), IdNameContainerDto.class));
        dancerDto.setTeacher(mapper.map(dancerEntity.getSchoolTeacher(), IdNameContainerDto.class));
        dancerDto.setSchool(mapper.map(dancerEntity.getSchoolStudent(), IdNameContainerDto.class));
        dancerDto.setEventsOrganizer(mapper.mapAsList(dancerEntity.getEventsOrganizer(), IdNameContainerDto.class));

        return dancerDto;
    }

}
