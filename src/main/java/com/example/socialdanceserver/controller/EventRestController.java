package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.EventTo;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.util.EventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = EventRestController.REST_URL)
public class EventRestController {

    static final String REST_URL = "/events";

    @Autowired
    private ImageStorageService imageStorageService;

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

    @ResponseBody
    @PostMapping(value = "/upload-image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) int id,
                              @RequestPart(value = "image", required = false)MultipartFile image){
        if (image != null){
            EventTo eventTo = get(id);
            if (eventTo.getImage() != null){
                imageStorageService.deleteImage(eventTo.getImage());
            }
            eventTo.setImage(imageStorageService.uploadImage(image));
            save(eventTo);
            return "uploaded";
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id",required = false) int id){
        EventTo eventTo = get(id);
        imageStorageService.deleteImage(eventTo.getImage());
        eventTo.setImage(null);
        save(eventTo);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) int id){
        EventTo eventTo = get(id);
        Resource resource = null;
        if (eventTo.getImage() != null){
            resource = imageStorageService.downloadImage(eventTo.getImage());
            if (resource == null){
                eventTo.setImage(null);
                save(eventTo);
            }
        }
        return ResponseEntity.ok().body(resource);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        eventService.deleteById(id);
    }
}
