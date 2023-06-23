package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.RatingDto;
import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = SchoolRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class SchoolRestController extends BaseController {

    static final String REST_URL = "/schools";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public PageDto<SchoolDto> getSchools(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "city", required = false) String city,
                                      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                      @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        log.info("Get schools name: {}, city: {}, pageNumber: {}, size: {}", name, city, pageNumber, size);

        return schoolService.getPageSchools(name, city, pageNumber, size);
    }

    @GetMapping("/{id}")
    public SchoolDto getById(@PathVariable UUID id) {
        log.info("Get school by uuid: {}", id);
        return schoolService.getById(id);
    }

    @PostMapping
    public SchoolDto save(@RequestBody SchoolDto school) {
        if (school.getId() != null){
            log.info("Update school with uuid: {}", school.getId());
        } else {
            log.info("Create new school");
        }

        return schoolService.save(school);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("Delete school with uuid: {}", id);
        deleteImage(id);
        schoolService.deleteById(id);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) UUID id,
                              @RequestPart(value = "file", required = false) MultipartFile file){
        if (file != null){
            SchoolDto schoolDto = schoolService.getById(id);
            if (schoolDto.getImage() != null && !schoolDto.getImage().isBlank()){
                imageStorageService.deleteImage(schoolDto.getImage());
            }

            String url = imageStorageService.uploadImage(file);
            schoolDto.setImage(url);
            schoolService.save(schoolDto);
            return url;
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id", required = false) UUID id){
        SchoolDto school = schoolService.getById(id);
        imageStorageService.deleteImage(school.getImage());
        school.setImage(null);
        save(school);
    }

}
