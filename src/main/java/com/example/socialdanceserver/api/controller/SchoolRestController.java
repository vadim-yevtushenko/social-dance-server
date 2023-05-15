package com.example.socialdanceserver.api.controller;

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
import java.util.List;
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
    public List<SchoolDto> schools() {
        log.info("Get all schools");
        return schoolService.getAll();
    }

    @GetMapping("/search/{name}")
    public List<SchoolDto> schoolsByName(@PathVariable String name) {
        log.info("Get all schools by name: {}", name);
        return schoolService.getAllByName(name);
    }

    @GetMapping("/search/{city}")
    public List<SchoolDto> schoolsByCity(@PathVariable String city) {
        log.info("Get all schools by city: {}", city);
        return schoolService.getAllByCity(city);
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
//        deleteImage(id);
        schoolService.deleteById(id);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) UUID id,
                              @RequestPart(value = "file", required = false) MultipartFile file){
        if (file != null){
            SchoolDto school = schoolService.getById(id);
            if (school.getImage() != null){
                imageStorageService.deleteImage(school.getImage());
            }
            school.setImage(imageStorageService.uploadImage(file));
            save(school);
            return "uploaded";
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

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) UUID id){
        SchoolDto school = schoolService.getById(id);
        Resource resource = null;
        if (school.getImage() != null){
            resource = imageStorageService.downloadImage(school.getImage());
            if (resource == null){
                school.setImage(null);
                save(school);
            }
        }
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/{id}/{dancerId}")
    public Integer getRatingByDancerId(@PathVariable UUID id, @PathVariable UUID dancerId) {
//        SchoolEntity schoolEntity = schoolService.getById(id);
//        if (schoolEntity.getRatings() == null) {
//            return 0;
//        }
//        for (RatingEntity schoolRatingEntity : schoolEntity.getRatings()) {
//            if (schoolRatingEntity.getReviewer_id() == dancerId) {
//                return schoolRatingEntity.getRating();
//            }
//        }
        return 0;
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewDto> findReviewsBySchool(@PathVariable UUID id) {
        return schoolService.getReviewsBySchoolId(id);
    }

    @PostMapping("/ratings")
    public void saveRating(@RequestBody RatingDto ratingDto) {
    }

    @PostMapping("/reviews")
    public void saveReview(@RequestBody ReviewDto reviewDto) {
    }
}
