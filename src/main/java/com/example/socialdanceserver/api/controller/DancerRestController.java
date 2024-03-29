package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.ImageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = DancerRestController.REST_URL)
public class DancerRestController extends BaseController{

    static final String REST_URL = "/dancers";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private DancerService dancerService;


    @GetMapping
    public PageDto<DancerDto> getDancers (@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "lastName", required = false) String lastName,
                                          @RequestParam(value = "country", required = false) String country,
                                          @RequestParam(value = "city", required = false) String city,
                                          @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                          @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        log.info("Get dancers name: {}, lastName: {}, city: {}, pageNumber: {}, size: {}", name, lastName, city, pageNumber, size);

        return dancerService.getPageDancers(name, lastName, country, city, pageNumber, size);
    }

    @GetMapping("/{id}")
    public DancerDto getById(@PathVariable UUID id){
        log.info("Get dancer by uuid: {}", id);
        DancerDto dancerDto = dancerService.getById(id);
        validateFound(dancerDto, DancerDto.class, id);

        return dancerDto;
    }

    @PostMapping
    public DancerDto save(@RequestBody DancerDto dancerDto){
        if (dancerDto.getId() != null){
            log.info("Update dancer with uuid: {}", dancerDto.getId());
        } else {
            log.info("Create new dancer");
        }

        return dancerService.save(dancerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id,
                       @RequestParam(value = "password") String password){
        log.info("Delete dancer with uuid: {}", id);

        dancerService.deleteById(id, password);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "id") UUID id,
                             @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) {
            DancerDto dancerDto = dancerService.getById(id);
            validateFound(dancerDto, DancerDto.class, id);
            if (dancerDto.getImage() != null && !dancerDto.getImage().isBlank()) {
                imageStorageService.deleteImage(dancerDto.getImage());
            }

            String url = imageStorageService.uploadImage(file);
            dancerDto.setImage(url);
            dancerService.save(dancerDto);
            return url;
        }

        return "";
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id", required = false) UUID id){
        DancerDto dancerDto = dancerService.getById(id);
        validateFound(dancerDto, DancerDto.class, id);
        imageStorageService.deleteImage(dancerDto.getImage());
        dancerDto.setImage(null);
        dancerService.save(dancerDto);
    }

}
