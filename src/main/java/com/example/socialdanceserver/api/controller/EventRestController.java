package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.exceptions.notfound.NotFoundException;
import com.example.socialdanceserver.service.EventService;
import com.example.socialdanceserver.service.ImageStorageService;
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
@CrossOrigin(origins = "http://localhost:3000")
public class EventRestController extends BaseController {

    static final String REST_URL = "/events";

    @Autowired
    private ImageStorageService imageStorageService;

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
        log.info("Get event by organizer uuid: {}", id);
        return eventService.getAllByOrganizerId(id);
    }

    @GetMapping("/school-organizer/{id}")
    public List<EventDto> getEventsBySchoolOrganizerId(@PathVariable UUID id){
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
        EventDto eventDto = getById(id);
        eventService.deleteById(id);
        if (eventDto.getImage() != null && !eventDto.getImage().equals("")) {
            imageStorageService.deleteImage(eventDto.getImage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) UUID id,
                              @RequestPart(value = "file", required = false)MultipartFile file) {
        if (file != null){
            EventDto eventDto = eventService.getById(id);
            if (eventDto.getImage() != null && !eventDto.getImage().isBlank()){
                imageStorageService.deleteImage(eventDto.getImage());
            }
            String url = imageStorageService.uploadImage(file, id);
            eventDto.setImage(url);
            eventService.save(eventDto);
            return url;
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

}
