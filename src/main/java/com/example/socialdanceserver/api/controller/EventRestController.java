package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.dto.EventDto;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.ImageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = EventRestController.REST_URL)
public class EventRestController extends BaseController {

    static final String REST_URL = "/events";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<EventDto> events(){
        log.info("Get all events");
        return eventService.getAll();
    }

    @GetMapping("/organizer/{id}")
    public List<EventDto> eventsSchoolOrganizerId(@PathVariable UUID id){
        log.info("Get event by organizer uuid: {}", id);
        return eventService.getAllBySchoolOrganizerId(id);
    }

    @GetMapping("/search/{city}")
    public List<EventDto> eventsByCity(@PathVariable String city){
        log.info("Get all events by city: {}", city);
        return eventService.getAllByCity(city);
    }

    @GetMapping("/{id}")
    public EventDto getById(@PathVariable UUID id){
        log.info("Get event by uuid: {}", id);
        return eventService.getById(id);
    }

    @PostMapping
    public EventDto save(@RequestBody EventDto eventDto){
        if (eventDto.getId() != null){
            log.info("Update event with uuid: {}", eventDto.getId());
        } else {
            log.info("Create new event");
        }

        return eventService.save(eventDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id){
        log.info("Delete event with uuid: {}", id);
//        deleteImage(id);
        eventService.deleteById(id);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) UUID id,
                              @RequestPart(value = "file", required = false)MultipartFile file){
        if (file != null){
            EventDto eventDto = getById(id);
            if (eventDto.getImage() != null){
                imageStorageService.deleteImage(eventDto.getImage());
            }
            eventDto.setImage(imageStorageService.uploadImage(file));
            save(eventDto);
            return "uploaded";
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id",required = false) UUID id){
        EventDto eventDto = getById(id);
        imageStorageService.deleteImage(eventDto.getImage());
        eventDto.setImage(null);
        save(eventDto);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) UUID id){
        EventDto eventDto = getById(id);
        Resource resource = null;
        if (eventDto.getImage() != null){
            resource = imageStorageService.downloadImage(eventDto.getImage());
            if (resource == null){
                eventDto.setImage(null);
                save(eventDto);
            }
        }
        return ResponseEntity.ok().body(resource);
    }

}
