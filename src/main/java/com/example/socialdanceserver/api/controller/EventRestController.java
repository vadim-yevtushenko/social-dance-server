package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = EventRestController.REST_URL)
public class EventRestController extends BaseController {

    static final String REST_URL = "/events";

    @Autowired
    private EventService eventService;

    @GetMapping
    public PageDto<EventDto> getEvents(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "country", required = false) String country,
                                       @RequestParam(value = "city", required = false) String city,
                                       @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                       @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        log.info("Get events name: {}, country: {}, city: {},pageNumber: {}, size: {}", name, country, city, pageNumber, size);

        return eventService.getPageEvents(name, country, city, pageNumber, size);
    }

    @GetMapping("/organizer/{id}")
    public List<EventDto> getEventsByOrganizerId(@PathVariable UUID id){
        log.info("Get events by organizer uuid: {}", id);
        return eventService.getAllByOrganizerId(id);
    }

    @GetMapping("/school-organizer/{id}")
    public List<EventDto> getEventsBySchoolOrganizerId(@PathVariable UUID id){
        log.info("Get event by school organizer uuid: {}", id);
        return eventService.getAllBySchoolOrganizerId(id);
    }

    @GetMapping("/{id}")
    public EventDto getById(@PathVariable UUID id){
        log.info("Get event by uuid: {}", id);
        EventDto eventDto = eventService.getById(id);
        validateFound(eventDto, EventDto.class, id);
        return eventDto;
    }

    @PostMapping
    public EventDto save(@RequestParam(value = "organizerId") UUID organizerId,
                         @RequestBody EventDto eventDto){
        if (eventDto.getId() != null){
            log.info("Update event with uuid: {}, organizer uuid: {}", eventDto.getId(), organizerId);
        } else {
            log.info("Create new event, organizer uuid: {}", organizerId);
        }

        return eventService.saveWithCheck(eventDto, organizerId);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id,
                @RequestParam(value = "organizerId") UUID organizerId){
        log.info("Delete event with uuid: {}, organizer uuid: {}", id, organizerId);
        eventService.deleteByIdWithCheck(id, organizerId);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id") UUID id,
                              @RequestParam(value = "organizerId") UUID organizerId,
                              @RequestPart(value = "file", required = false)MultipartFile file) {

        return eventService.uploadEventImage(id, organizerId, file);
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id") UUID id,
                            @RequestParam(value = "organizerId") UUID organizerId){
        eventService.deleteEventImage(id, organizerId);
    }

    @PostMapping(value = "/notify-created")
    void sendNotificationOfCreation(@RequestBody EventDto event) {
        eventService.sendEmailsWhenEventCreated(event);
    }

}
