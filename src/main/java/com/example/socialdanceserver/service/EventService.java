package com.example.socialdanceserver.service;


import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.Event;

import java.util.List;

public interface EventService {

    List<AbstractBaseEntity> getAll();

    Event getById(int id);

    Event create(Event event);

    void update(Event event);

    void deleteById(int id);
}
