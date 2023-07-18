package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.EmailDto;
import com.example.socialdanceserver.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AdminRestController.REST_URL)
public class AdminRestController extends BaseController{

    static final String REST_URL = "/admin";

    @Autowired
    private AdminService adminService;

    @PostMapping("/support")
    public void receiveSupportEmail(@RequestBody EmailDto email) {
        adminService.receiveSupportEmail(email);
    }
}
