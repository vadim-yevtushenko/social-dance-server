package com.example.socialdanceserver.controller;


import com.example.socialdanceserver.model.AbstractBaseEntity;
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
    public List<AbstractBaseEntity> dancers (){
        return dancerService.getAll();
    }

    @GetMapping("/{id}")
    public Dancer get(@PathVariable int id){
        return dancerService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dancer create(@RequestBody Dancer dancer){
        return dancerService.create(dancer);
    }

//    @PostMapping
//    public void update(@RequestBody Dancer dancer){
//        dancerService.update(dancer);
//    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        dancerService.deleteById(id);
    }
}
