package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.service.CredentialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = CredentialRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class CredentialRestController extends BaseController{

    static final String REST_URL = "/credential";

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/login")
    public DancerDto login (@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return credentialService.login(email, password);
    }

    @PostMapping(value = "/registration")
    public DancerDto registration (@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestBody DancerDto dancer){

        return credentialService.registration(email, password, dancer);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestParam(value = "email") String email,
                                  @RequestParam(value = "newPassword") String newPassword,
                               @RequestParam(value = "oldPassword") String oldPassword){
        credentialService.changePassword(email, newPassword, oldPassword);
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestParam(value = "email") String email,
                               @RequestParam(value = "newEmail") String newEmail){
        return credentialService.changeEmail(email, newEmail);
    }

}
