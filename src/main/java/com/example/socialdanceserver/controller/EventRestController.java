package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.EventDto;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.mapper.EventMapper;
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
    public List<EventDto> events(){
        return EventMapper.mapEventTos(eventService.getAllByType());
    }

    @GetMapping("/owner/{id}")
    public List<EventDto> eventsByOwner(@PathVariable int id){
        return EventMapper.mapEventTos(eventService.getAllByOwnerId(id));
    }

    @GetMapping("/search/{city}")
    public List<EventDto> eventsByCity(@PathVariable String city){
        return EventMapper.mapEventTos(eventService.getAllByCity(city));
    }

    @GetMapping("/{id}")
    public EventDto get(@PathVariable int id){
        return EventMapper.mapEventTo(eventService.getById(id));
    }

    @PostMapping
    public EventDto save(@RequestBody EventDto eventDto){
        return eventService.save(eventDto);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) int id,
                              @RequestPart(value = "file", required = false)MultipartFile file){
        if (file != null){
            EventDto eventDto = get(id);
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
    public void deleteImage(@RequestParam(value = "id",required = false) int id){
        EventDto eventDto = get(id);
        imageStorageService.deleteImage(eventDto.getImage());
        eventDto.setImage(null);
        save(eventDto);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) int id){
        EventDto eventDto = get(id);
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

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        deleteImage(id);
        eventService.deleteById(id);
    }
}
