package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.util.EventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = EventRestController.REST_URL)
public class EventRestController {

    static final String REST_URL = "/events";

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventTo> events(){
        return EventUtils.getEventTos(eventService.getAllByType());
    }

    @GetMapping("/owner/{id}")
    public List<EventTo> eventsByOwner(@PathVariable int id){
        return EventUtils.getEventTos(eventService.getAllByOwnerId(id));
    }

    @GetMapping("/search/{city}")
    public List<EventTo> eventsByCity(@PathVariable String city){
        return EventUtils.getEventTos(eventService.getAllByCity(city));
    }

    @GetMapping("/{id}")
    public EventTo get(@PathVariable int id){
        return EventUtils.createEventTo(eventService.getById(id));
    }

    @PostMapping
    public EventTo save(@RequestBody EventTo eventTo){
        return eventService.save(eventTo);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        eventService.deleteById(id);
    }
}
