package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.dao.EventDao;
import com.example.socialdanceserver.persistence.entity.EventEntity;
import com.example.socialdanceserver.persistence.repository.EventRepository;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDao eventDao;

    @Override
    public PageDto<EventDto> getPageEvents(String name, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = eventDao.getMapPredicates(name, city);
        int total = eventDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("name"), mapPredicates, pageNumber, size, total);

        List<EventEntity> eventEntities = eventDao.load(paginationRequest);

        return new PageDto<>(total, mapper.mapAsList(eventEntities, EventDto.class));
    }

    @Override
    public List<EventDto> getAllBySchoolOrganizerId(UUID id) {
        return mapper.mapAsList(eventRepository.findDistinctBySchoolOrganizerId(id), EventDto.class);
    }

    @Override
    public EventDto getById(UUID id) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(id);
        return optionalEventEntity.map(entity -> mapper.map(entity, EventDto.class)).orElse(null);
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
