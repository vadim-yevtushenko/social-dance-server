package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.model.Event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EventUtils {

    public static List<EventTo> getEventTos(List<Event> eventList){
        return eventList.stream().map(EventUtils::createEventTo).
                collect(Collectors.toList());
    }

    public static EventTo createEventTo(Event event){
        return new EventTo(event.getId(), event.getImage(), event.getName(), event.getDescription(),
                event.getEntityInfo(), DateTimeUtils.fromLocalDateTimeToDate(event.getDateEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(event.getDateFinishEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(event.getDatePublication()),
                new ArrayList<>(event.getDances()), event.getOwnerId());
    }

    public static Event fromEventTo(EventTo eventTo, Event event) {
        event.setImage(eventTo.getImage());
        event.setName(eventTo.getName());
        event.setOwnerId(eventTo.getOwnerId());
        event.setDescription(eventTo.getDescription());
        event.setEntityInfo(eventTo.getEntityInfo());
        event.setDances(new HashSet<>(eventTo.getDances()));
        event.setDatePublication(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDatePublication()));
        event.setDateEvent(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDateEvent()));
        event.setDateFinishEvent(DateTimeUtils.fromDateToLocalDateTime(eventTo.getDateFinishEvent()));
        return event;
    }
}
