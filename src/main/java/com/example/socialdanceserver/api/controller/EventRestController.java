package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.ImageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private ImageStorageService imageStorageService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public PageDto<EventDto> getEvents(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "city", required = false) String city,
                                       @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                       @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        log.info("Get events name: {}, city: {}, pageNumber: {}, size: {}", name, city, pageNumber, size);

        return eventService.getPageEvents(name, city, pageNumber, size);
    }

    @GetMapping("/organizer/{id}")
    public List<EventDto> eventsSchoolOrganizerId(@PathVariable UUID id){
        log.info("Get event by organizer uuid: {}", id);
        return eventService.getAllBySchoolOrganizerId(id);
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
