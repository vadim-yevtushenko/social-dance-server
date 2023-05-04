package com.example.socialdanceserver.service;


import com.example.socialdanceserver.dto.EventDto;
import java.util.List;
import java.util.UUID;

public interface EventService {

    List<EventDto> getAll();

    List<EventDto> getAllByOwnerId(UUID id);

    List<EventDto> getAllByCity(String city);

    EventDto getById(UUID id);

    EventDto save(EventDto eventDto);

//    void update(Event event);

    void deleteById(UUID id);
}
