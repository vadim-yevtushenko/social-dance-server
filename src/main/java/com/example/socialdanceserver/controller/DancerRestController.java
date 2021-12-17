package com.example.socialdanceserver.controller;


import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.util.CustomDateSerializer;
import com.example.socialdanceserver.util.DancerUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    public List<DancerTo> dancers (){
        return DancerUtils.getDancerTos(dancerService.getAllByType());
    }

    @GetMapping("/{id}")
    public DancerTo get(@PathVariable int id){
        Dancer dancer = dancerService.getById(id);
        return  dancer != null ? DancerUtils.createDancerTo(dancer) : new DancerTo();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DancerTo save(@RequestBody DancerTo dancerTo){
        return dancerService.save(dancerTo);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        dancerService.deleteById(id);
    }

    @GetMapping("/registration/{email}")
    public Integer checkSignUp(@PathVariable String email){
        return dancerService.checkSignUpByEmail(email);
    }

    @GetMapping("/identification/{email}/{password}")
    public Integer checkSignIn(@PathVariable String email, @PathVariable String password){
        return dancerService.checkSignIpByEmailAndPassword(email, password);
    }
}
