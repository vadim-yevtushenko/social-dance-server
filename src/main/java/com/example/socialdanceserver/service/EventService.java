package com.example.socialdanceserver.service;


import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Event;

import java.util.List;

public interface EventService {

    List<AbstractBaseEntity> getAll();

    List<Event> getAllByType();

    List<Event> getAllByOwnerId(int id);

    Event getById(int id);

    EventTo save(EventTo eventTo);

//    void update(Event event);

    void deleteById(int id);
}
