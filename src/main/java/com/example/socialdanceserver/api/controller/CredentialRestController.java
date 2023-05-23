package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.service.CredentialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = CredentialRestController.REST_URL)
public class CredentialRestController extends BaseController{

    static final String REST_URL = "/credential";

    @Autowired
    private CredentialService credentialService;

    @GetMapping
    public DancerDto login (@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return credentialService.login(email, password);
    }

    @PostMapping
    public DancerDto registration (@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "name") String name){

        return credentialService.registration(email, password, name);
    }

}
