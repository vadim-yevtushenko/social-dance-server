package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.model.Event;
import com.example.socialdanceserver.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.util.DancerUtils;
import com.example.socialdanceserver.util.EventUtils;
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
    public List<Event> getAllByType() {
        return eventRepository.findAllByType();
    }

    @Override
    public List<Event> getAllByOwnerId(int id) {
        return eventRepository.findAllByOwnerId(id);
    }

    @Override
    public Event getById(int id) {
        Event event = null;
        Optional<AbstractBaseEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()){
            event = (Event) eventOptional.get();
        }
        return event;
    }

    @Override
    public EventTo save(EventTo eventTo) {
        Event oldEvent = new Event();
        if (eventTo.getId() != null){
            oldEvent = getById(eventTo.getId());
        }
        Event event = EventUtils.fromEventTo(eventTo, oldEvent);
        return EventUtils.createEventTo(eventRepository.save(event));
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
