package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.EventEntity;
import com.example.socialdanceserver.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<AbstractBaseEntity> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventEntity> getAllByType() {
        return eventRepository.findAllByType();
    }

    @Override
    public List<EventEntity> getAllByOwnerId(int id) {
        return eventRepository.findAllByOwnerId(id);
    }

    @Override
    public List<EventEntity> getAllByCity(String city) {
        return eventRepository.findAllByCity(city);
    }

    @Override
    public EventEntity getById(int id) {
        EventEntity eventEntity = null;
        Optional<AbstractBaseEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()){
            eventEntity = (EventEntity) eventOptional.get();
        }
        return eventEntity;
    }

    @Override
    public EventTo save(EventTo eventTo) {
        EventEntity oldEventEntity = new EventEntity();
        if (eventTo.getId() != null){
            oldEventEntity = getById(eventTo.getId());
        }
        EventEntity eventEntity = EventMapper.populateEventTo(eventTo, oldEventEntity);
        return EventMapper.mapEventTo(eventRepository.save(eventEntity));
    }

//    @Override
//    public void update(Event event) {
//        eventRepository.save(event);
//    }

    @Override
    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }
}
