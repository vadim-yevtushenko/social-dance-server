package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventUtils {

    public static List<EventTo> getEventTos(List<Event> eventList){
        return eventList.stream().map(EventUtils::createEventTo).
                collect(Collectors.toList());
    }

    public static EventTo createEventTo(Event event){
        return new EventTo(event.getId(), event.getName(), event.getDescription(),
                event.getEntityInfo(), DateTimeUtils.fromLocalDateTimeToDate(event.getDateEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(event.getDateFinishEvent()),
                DateTimeUtils.fromLocalDateTimeToDate(event.getDatePublication()),
                new ArrayList<>(event.getDances()), event.getOwnerId());
    }
}
