package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.DanceDto;
import com.example.socialdanceserver.service.UtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = UtilRestController.REST_URL)
@CrossOrigin(origins = "http://localhost:3000")
public class UtilRestController extends BaseController{

    static final String REST_URL = "/utils";

    @Autowired
    private UtilService utilService;

    @GetMapping("/dances")
    public List<DanceDto> getAllDances() {
        return utilService.getDances();
    }
}
