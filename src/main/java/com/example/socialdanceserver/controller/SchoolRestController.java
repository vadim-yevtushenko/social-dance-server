package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.dto.RatingTo;
import com.example.socialdanceserver.dto.ReviewTo;
import com.example.socialdanceserver.dto.SchoolTo;
import com.example.socialdanceserver.model.RatingEntity;
import com.example.socialdanceserver.model.SchoolEntity;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.service.SchoolService;
import com.example.socialdanceserver.mapper.SchoolMapper;
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
        return SchoolMapper.mapSchoolTos(schoolService.getAllByType());
    }

    @GetMapping("/owner/{id}")
    public List<SchoolTo> schoolsByOwner(@PathVariable int id) {
        return SchoolMapper.mapSchoolTos(schoolService.getAllByOwnerId(id));
    }

    @GetMapping("/search/{city}")
    public List<SchoolTo> schoolsByCity(@PathVariable String city) {
        return SchoolMapper.mapSchoolTos(schoolService.getAllByCity(city));
    }

    @GetMapping("/{id}")
    public SchoolTo get(@PathVariable int id) {
        return SchoolMapper.mapSchoolTo(schoolService.getById(id));
    }

    @GetMapping("/{id}/{dancerId}")
    public Integer getRatingByDancerId(@PathVariable int id, @PathVariable int dancerId) {
        SchoolEntity schoolEntity = schoolService.getById(id);
        if (schoolEntity.getRatings() == null) {
            return 0;
        }
        for (RatingEntity schoolRatingEntity : schoolEntity.getRatings()) {
            if (schoolRatingEntity.getReviewer_id() == dancerId) {
                return schoolRatingEntity.getRating();
            }
        }
        return 0;
    }

    @PostMapping
    public SchoolEntity save(@RequestBody SchoolEntity schoolEntity) {
        return schoolService.save(schoolEntity);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestParam(value = "id", required = false) int id,
                              @RequestPart(value = "file", required = false) MultipartFile file){
        System.out.println(file);
        if (file != null){
            SchoolEntity schoolEntity = schoolService.getById(id);
            if (schoolEntity.getImage() != null){
                imageStorageService.deleteImage(schoolEntity.getImage());
            }
            schoolEntity.setImage(imageStorageService.uploadImage(file));
            save(schoolEntity);
            return "uploaded";
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteImage(@RequestParam(value = "id", required = false) int id){
        SchoolEntity schoolEntity = schoolService.getById(id);
        imageStorageService.deleteImage(schoolEntity.getImage());
        schoolEntity.setImage(null);
        save(schoolEntity);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadImage(@RequestParam(value = "id", required = false) int id){
        SchoolEntity schoolEntity = schoolService.getById(id);
        Resource resource = null;
        if (schoolEntity.getImage() != null){
            resource = imageStorageService.downloadImage(schoolEntity.getImage());
            if (resource == null){
                schoolEntity.setImage(null);
                save(schoolEntity);
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
        return SchoolMapper.mapReviewTos(schoolService.getReviewsBySchoolId(id));
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
