package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.dao.EventDao;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.entity.EventEntity;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.persistence.repository.EventRepository;
import com.example.socialdanceserver.persistence.repository.SchoolRepository;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DancerService dancerService;


    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private EventDao eventDao;

    @Override
    public PageDto<EventDto> getPageEvents(String name, String country, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = eventDao.getMapPredicates(name, country, city);
        int total = eventDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("dateFinishEvent"), mapPredicates, pageNumber, size, total);

        List<EventEntity> eventEntities = eventDao.load(paginationRequest);

        return new PageDto<>(total, mapper.mapAsList(eventEntities, EventDto.class));
    }

    @Override
    public List<EventDto> getAllByOrganizerId(UUID id) {
        DancerDto dancerDto = dancerService.getById(id);
        if (dancerDto != null) {
            return mapper.mapAsList(eventRepository
                    .findDistinctByOrganizersInOrderByCreated(Set.of(mapper.map(dancerDto, DancerEntity.class))), EventDto.class);
        }
        return null;
    }

    @Override
    public List<EventDto> getAllBySchoolOrganizerId(UUID id) {
        return mapper.mapAsList(eventRepository.findDistinctBySchoolOrganizerIdOrderByCreated(id), EventDto.class);
    }

    @Override
    public EventDto getById(UUID id) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(id);
        return optionalEventEntity.map(entity -> mapper.map(entity, EventDto.class)).orElse(null);
    }

    @Override
    public EventDto save(EventDto eventDto) {
        EventEntity eventEntity = mapper.map(eventDto, EventEntity.class);
        if (eventDto.getSchoolOrganizer() != null) {
            SchoolEntity schoolEntity = schoolRepository.getById(eventDto.getSchoolOrganizer().getId());
            eventEntity.setSchoolOrganizer(schoolEntity);
        }

        return mapper.map(eventRepository.save(eventEntity), EventDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        eventRepository.deleteById(id);
    }

}
