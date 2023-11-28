package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Max;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = ReviewRestController.REST_URL)
public class ReviewRestController extends BaseController{

    static final String REST_URL = "/reviews";

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public PageDto<ReviewDto> getReviewsBySchool(@RequestParam(value = "objectId", required = false) UUID objectId,
                                                 @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                                 @RequestParam(value = "size", defaultValue = "10") @Max(50) int size){
        return reviewService.getPageReviewsByObjectId(objectId, pageNumber, size);
    }

    @PostMapping
    public ReviewDto save(@RequestBody ReviewDto reviewDto) {
        return reviewService.save(reviewDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        reviewService.deleteById(id);
    }
}
