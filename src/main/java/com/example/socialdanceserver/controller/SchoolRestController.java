package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.RatingTo;
import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.dto.SchoolTo;
import com.example.socialdanceserver.model.Rating;
import com.example.socialdanceserver.model.School;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.service.SchoolService;
import com.example.socialdanceserver.util.SchoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = SchoolRestController.REST_URL)
public class SchoolRestController {

    static final String REST_URL = "/schools";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<SchoolTo> schools() {
        return SchoolUtils.getSchoolTos(schoolService.getAllByType());
    }

    @GetMapping("/owner/{id}")
    public List<SchoolTo> schoolsByOwner(@PathVariable int id) {
        return SchoolUtils.getSchoolTos(schoolService.getAllByOwnerId(id));
    }

    @GetMapping("/search/{city}")
    public List<SchoolTo> schoolsByCity(@PathVariable String city) {
        return SchoolUtils.getSchoolTos(schoolService.getAllByCity(city));
    }

    @GetMapping("/{id}")
    public SchoolTo get(@PathVariable int id) {
        return SchoolUtils.createSchoolTo(schoolService.getById(id));
    }

    @GetMapping("/{id}/{dancerId}")
    public Integer getRatingByDancerId(@PathVariable int id, @PathVariable int dancerId) {
        School school = schoolService.getById(id);
        if (school.getRatings() == null) {
            return 0;
        }
        for (Rating schoolRating : school.getRatings()) {
            if (schoolRating.getReviewer_id() == dancerId) {
                return schoolRating.getRating();
            }
        }
        return 0;
    }

    @PostMapping
    public School save(@RequestBody School school) {
        return schoolService.save(school);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) int id,
                              @RequestPart(value = "file", required = false) MultipartFile file){
        System.out.println(file);
        if (file != null){
            School school = schoolService.getById(id);
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
    public void deleteImage(@RequestParam(value = "id", required = false) int id){
        School school = schoolService.getById(id);
        imageStorageService.deleteImage(school.getImage());
        school.setImage(null);
        save(school);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) int id){
        School school = schoolService.getById(id);
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        deleteImage(id);
        schoolService.deleteById(id);
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewTo> findReviewsBySchool(@PathVariable int id) {
        return SchoolUtils.getReviewTos(schoolService.getReviewsBySchoolId(id));
    }

    @PostMapping("/ratings")
    public void saveRating(@RequestBody RatingTo ratingTo) {
        schoolService.saveRating(ratingTo);
    }

    @PostMapping("/reviews")
    public void saveReview(@RequestBody ReviewTo reviewTo) {
        schoolService.createReview(reviewTo);
    }
}
