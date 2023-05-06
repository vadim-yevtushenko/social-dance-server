package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.dto.EventDto;
import com.example.socialdanceserver.model.EventEntity;
import com.example.socialdanceserver.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventDto> getAll() {
        return mapper.mapAsList(eventRepository.findAll(), EventDto.class);
    }

    @Override
    public List<EventDto> getAllByOwnerId(UUID id) {
        return mapper.mapAsList(eventRepository.findBySchoolOrganizerId(id), EventDto.class);
    }

    @Override
    public List<EventDto> getAllByCity(String city) {
        return mapper.mapAsList(eventRepository.findByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(city), EventDto.class);
    }

    @Override
    public EventDto getById(UUID id) {
        EventEntity eventEntity = null;
        Optional<EventEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()){
            eventEntity = eventOptional.get();
        }
        return mapper.map(eventEntity, EventDto.class);
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
