package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.persistence.entity.EventEntity;
import com.example.socialdanceserver.persistence.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventDto> getAll() {
        return mapper.mapAsList(eventRepository.findDistinctAllEvents(), EventDto.class);
    }

    @Override
    public List<EventDto> getAllBySchoolOrganizerId(UUID id) {
        return mapper.mapAsList(eventRepository.findDistinctBySchoolOrganizerId(id), EventDto.class);
    }

    @Override
    public List<EventDto> getAllByName(String name) {
        return mapper.mapAsList(eventRepository.findDistinctByNameContainsIgnoreCaseOrderByContactInfo_CityAsc(name), EventDto.class);
    }

    @Override
    public List<EventDto> getAllByCity(String city) {
        return mapper.mapAsList(eventRepository.findDistinctByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(city), EventDto.class);
    }

    @Override
    public EventDto getById(UUID id) {
        return mapper.map(eventRepository.findDistinctEventEntityById(id), EventDto.class);
    }

    @Override
    public EventDto save(EventDto eventDto) {
        EventEntity oldEventEntity = new EventEntity();
//        if (eventDto.getId() != null){
//            oldEventEntity = eventRepository.getById(eventDto.getId());
//        }
//        eventDto.setDateEvent(ZonedDateTime.now().plusDays(10).plusHours(15));
//        eventDto.setDateFinishEvent(ZonedDateTime.now().plusDays(10).plusHours(19));
        EventEntity eventEntity = mapper.map(eventDto, EventEntity.class);
        return mapper.map(eventRepository.save(eventEntity), EventDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        eventRepository.deleteById(id);
    }

}
