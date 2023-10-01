package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = SchoolRestController.REST_URL)
public class SchoolRestController extends BaseController {

    static final String REST_URL = "/schools";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public PageDto<SchoolDto> getSchools(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "country", required = false) String country,
                                      @RequestParam(value = "city", required = false) String city,
                                      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                      @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        log.info("Get schools name: {}, country: {}, city: {}, pageNumber: {}, size: {}", name, country, city, pageNumber, size);

        return schoolService.getPageSchools(name, country, city, pageNumber, size);
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
        SchoolDto school = schoolService.getById(id);
        schoolService.deleteById(id);
        if (school.getImage() != null && !school.getImage().equals("")){
            imageStorageService.deleteImage(school.getImage());
        }
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

            String url = imageStorageService.uploadImage(file, id);
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
