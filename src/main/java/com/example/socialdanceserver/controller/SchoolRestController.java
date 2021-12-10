package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.model.School;
import com.example.socialdanceserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = SchoolRestController.REST_URL)
public class SchoolRestController {

    static final String REST_URL = "/schools";

    @Autowired
    private SchoolService schoolService;


    @GetMapping
    public List<School> schools (){
        return schoolService.getAllByType();
    }

    @GetMapping("/{id}")
    public School get(@PathVariable int id){
        return schoolService.getById(id);
    }

    @PostMapping
    public School save(@RequestBody School school){
        return schoolService.create(school);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        schoolService.deleteById(id);
    }
}
