package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Event;
import com.example.socialdanceserver.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
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
    public Event getById(int id) {
        Event event = null;
        Optional<AbstractBaseEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()){
            event = (Event) eventOptional.get();
        }
        return event;
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void update(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }
}
