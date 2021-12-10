package com.example.socialdanceserver.controller;

import com.example.socialdanceserver.model.LoginPassword;
import com.example.socialdanceserver.model.School;
import com.example.socialdanceserver.service.LoginPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = IdentificationController.REST_URL)
public class IdentificationController {

    static final String REST_URL = "/identification";

    @Autowired
    private LoginPasswordService service;

    @GetMapping
    public List<LoginPassword> LP (){
        return service.getAll();
    }

//    @GetMapping("/{id}")
//    public LoginPassword get(@PathVariable int id){
//        return service.getById(id).get();
//    }

    @GetMapping("/{email}")
    public LoginPassword get(@PathVariable String email){
        return service.getByEmail(email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginPassword save(@RequestBody LoginPassword loginPassword){
        return service.save(loginPassword);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        service.deleteByDancerId(id);
    }
}
