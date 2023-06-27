package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import java.util.List;
import java.util.UUID;

public interface EventService {

    PageDto<EventDto> getPageEvents(String name, String country, String city, int pageNumber, int size);

    List<EventDto> getAllByOrganizerId(UUID id);

    List<EventDto> getAllBySchoolOrganizerId(UUID id);

    EventDto getById(UUID id);

    EventDto save(EventDto eventDto);

//    void update(Event event);

    void deleteById(UUID id);
}
