package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.dtocontainer.IdNameContainerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.dao.DancerDao;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.persistence.repository.DancerRepository;
import com.example.socialdanceserver.service.*;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DancerServiceImpl extends BaseService implements DancerService {

    @Autowired
    private DancerRepository dancerRepository;

    @Autowired
    private CredentialService credentialService;

    @Lazy
    @Autowired
    private SchoolService schoolService;

    @Lazy
    @Autowired
    private EventService eventService;

    @Autowired
    private ImageStorageService imageStorageService;

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

    private void getDancerEntityFromDto(DancerEntity dancerEntity, DancerEntity oldDancerEntity) {

        dancerEntity.setEventsOrganizer(oldDancerEntity.getEventsOrganizer());
        dancerEntity.setSchoolAdministrator(oldDancerEntity.getSchoolAdministrator());
        dancerEntity.setSchoolTeacher(oldDancerEntity.getSchoolTeacher());
        dancerEntity.setSchoolStudent(oldDancerEntity.getSchoolStudent());
        dancerEntity.setEventParticipant(oldDancerEntity.getEventParticipant());

    }

    @Override
    public void deleteById(UUID id, String password) {
        credentialService.checkPassword(id, password);

        DancerEntity dancer = dancerRepository.findById(id).orElse(new DancerEntity());
        validateFound(dancer.getId(), UUID.class, id);
        if (dancer.getSchoolAdministrator() != null) {
            SchoolEntity school = dancer.getSchoolAdministrator();
            if (school.getAdministrators().size() == 1) {
                schoolService.deleteById(school.getId());
            }
        }
        if (dancer.getEventsOrganizer() != null) {
            dancer.getEventsOrganizer().forEach(event -> {
                if (event.getOrganizers().size() == 1) {
                    eventService.deleteById(event.getId());
                }
            });
        }
        if (dancer.getImage() != null && !dancer.getImage().equals("")){
            imageStorageService.deleteImage(dancer.getImage());
        }

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

    @Override
    public List<DancerEntity> getDancersByCity(String city) {
        return dancerRepository.findDancerEntitiesByContactInfo_City(city);
    }

    @Override
    public List<InternetAddress> getInternetAddressesByCity(String city) {
        List<String> emails = dancerRepository.fetchEmailsByCity(city);
        return emails.stream()
                .map(email -> {
                    try {
                        return new InternetAddress(email);
                    } catch (AddressException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

}
