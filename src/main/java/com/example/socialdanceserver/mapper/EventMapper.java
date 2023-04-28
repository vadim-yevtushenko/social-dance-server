package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.EventDto;
import com.example.socialdanceserver.model.EventEntity;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public static List<EventDto> mapEventTos(List<EventEntity> eventEntityList){
        return eventEntityList.stream().map(EventMapper::mapEventTo).
                collect(Collectors.toList());
    }

    public static EventDto mapEventTo(EventEntity eventEntity){
        return new EventDto(eventEntity.getId(), eventEntity.getImage(), eventEntity.getName(), eventEntity.getDescription(),
                eventEntity.getEntityInfo(), DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDateEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDateFinishEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDatePublication()),
                new ArrayList<>(eventEntity.getDances()), eventEntity.getOwnerId());
    }

    public static EventEntity populateEventTo(EventDto eventDto, EventEntity eventEntity) {
        eventEntity.setImage(eventDto.getImage());
        eventEntity.setName(eventDto.getName());
        eventEntity.setOwnerId(eventDto.getOwnerId());
        eventEntity.setDescription(eventDto.getDescription());
        eventEntity.setEntityInfo(eventDto.getEntityInfo());
        eventEntity.setDances(new HashSet<>(eventDto.getDances()));
        eventEntity.setDatePublication(DateTimeUtils.fromDateToLocalDateTime(eventDto.getDatePublication()));
        eventEntity.setDateEvent(DateTimeUtils.fromDateToLocalDateTime(eventDto.getDateEvent()));
        eventEntity.setDateFinishEvent(DateTimeUtils.fromDateToLocalDateTime(eventDto.getDateFinishEvent()));
        return eventEntity;
    }
}
