package com.example.socialdanceserver.controller;


import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DancerRestController.REST_URL)
public class DancerRestController {

    static final String REST_URL = "/dancers";

    @Autowired
    private DancerService dancerService;

    @GetMapping
    public List<Dancer> dancers (){
        return dancerService.getAllByType();
    }

    @GetMapping("/{id}")
    public Dancer get(@PathVariable int id){
        return dancerService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dancer save(@RequestBody Dancer dancer){
        return dancerService.create(dancer);
    }


    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        dancerService.deleteById(id);
    }
}
