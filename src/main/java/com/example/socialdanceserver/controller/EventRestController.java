package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.model.Event;
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

    @GetMapping("/{id}")
    public EventTo get(@PathVariable int id){
        return EventUtils.createEventTo(eventService.getById(id));
    }

    @PostMapping
    public Event save(@RequestBody Event event){
        return eventService.create(event);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        eventService.deleteById(id);
    }
}
