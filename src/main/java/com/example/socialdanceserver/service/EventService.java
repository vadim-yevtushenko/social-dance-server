package com.example.socialdanceserver.service;


import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.AbstractBaseEntity;
import com.example.socialdanceserver.model.EventEntity;

import java.util.List;

public interface EventService {

    List<AbstractBaseEntity> getAll();

    List<EventEntity> getAllByType();

    List<EventEntity> getAllByOwnerId(int id);

    List<EventEntity> getAllByCity(String city);

    EventEntity getById(int id);

    EventTo save(EventTo eventTo);

//    void update(Event event);

    void deleteById(int id);
}
