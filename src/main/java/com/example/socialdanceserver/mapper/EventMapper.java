package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.EventEntity;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public static List<EventTo> mapEventTos(List<EventEntity> eventEntityList){
        return eventEntityList.stream().map(EventMapper::mapEventTo).
                collect(Collectors.toList());
    }

    public static EventTo mapEventTo(EventEntity eventEntity){
        return new EventTo(eventEntity.getId(), eventEntity.getImage(), eventEntity.getName(), eventEntity.getDescription(),
                eventEntity.getEntityInfo(), DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDateEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDateFinishEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(eventEntity.getDatePublication()),
                new ArrayList<>(eventEntity.getDances()), eventEntity.getOwnerId());
    }

    public static EventEntity populateEventTo(EventTo eventTo, EventEntity eventEntity) {
        eventEntity.setImage(eventTo.getImage());
        eventEntity.setName(eventTo.getName());
        eventEntity.setOwnerId(eventTo.getOwnerId());
        eventEntity.setDescription(eventTo.getDescription());
        eventEntity.setEntityInfo(eventTo.getEntityInfo());
        eventEntity.setDances(new HashSet<>(eventTo.getDances()));
        eventEntity.setDatePublication(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDatePublication()));
        eventEntity.setDateEvent(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDateEvent()));
        eventEntity.setDateFinishEvent(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDateFinishEvent()));
        return eventEntity;
    }
}
