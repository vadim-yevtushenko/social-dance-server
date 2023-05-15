package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.EventDto;
import java.util.List;
import java.util.UUID;

public interface EventService {

    List<EventDto> getAll();

    List<EventDto> getAllBySchoolOrganizerId(UUID id);

    List<EventDto> getAllByName(String name);

    List<EventDto> getAllByCity(String city);

    EventDto getById(UUID id);

    EventDto save(EventDto eventDto);

//    void update(Event event);

    void deleteById(UUID id);
}
