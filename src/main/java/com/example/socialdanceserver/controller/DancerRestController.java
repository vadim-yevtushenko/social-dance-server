package com.example.socialdanceserver.controller;


import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.util.CustomDateSerializer;
import com.example.socialdanceserver.util.DancerUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/search/{city}")
    public List<DancerTo> dancersByCity(@PathVariable String city){
        return DancerUtils.getDancerTos(dancerService.getAllByCity(city));
    }

    @GetMapping("/search")
    public List<DancerTo> dancersByCity(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "surname", required = false) String surname){
        if (name.isEmpty()){
            return DancerUtils.getDancerTos(dancerService.getAllBySurname(surname));
        }else if (surname.isEmpty()){
            return DancerUtils.getDancerTos(dancerService.getAllByName(name));
        }
        return DancerUtils.getDancerTos(dancerService.getAllByNameAndSurname(name, surname));
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

    @GetMapping("/registration")
    public Integer checkSignUp(@RequestParam(value = "email", required = false) String email){

        return dancerService.checkSignUpByEmail(email);
    }

    @GetMapping("/identification")
    public Integer checkSignIn(@RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "password", required = false) String password){
        return dancerService.checkSignIpByEmailAndPassword(email, password);
    }
}
