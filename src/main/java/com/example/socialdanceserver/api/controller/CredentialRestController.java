package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.service.CredentialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = CredentialRestController.REST_URL)
public class CredentialRestController extends BaseController{

    static final String REST_URL = "/credentials";

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/login")
    public Map<String, Object> login (@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        return credentialService.login(email, password);
    }

    @PostMapping(value = "/registration")
    public Map<String, Object> registration (@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password,
                                   @RequestBody DancerDto dancer){

        return credentialService.registration(email, password, dancer);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam(value = "email") String email,
                                  @RequestParam(value = "newPassword") String newPassword,
                               @RequestParam(value = "oldPassword") String oldPassword){

        return credentialService.changePassword(email, newPassword, oldPassword);
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestParam(value = "email") String email,
                               @RequestParam(value = "newEmail") String newEmail,
                              @RequestParam(value = "password") String password){
        return credentialService.changeEmail(email, newEmail, password);
    }

    @PostMapping("/reset_password")
    public void resetPassword(@RequestParam(value = "email") String email){
        credentialService.resetPassword(email);
    }

}
