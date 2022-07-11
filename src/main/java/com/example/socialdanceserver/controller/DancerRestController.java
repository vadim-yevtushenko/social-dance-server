package com.example.socialdanceserver.controller;


import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.ImageStorageService;
import com.example.socialdanceserver.mapper.DancerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = DancerRestController.REST_URL)
public class DancerRestController {

    static final String REST_URL = "/dancers";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private DancerService dancerService;

    @GetMapping
    public List<DancerTo> dancers (){
        return DancerMapper.mapDancerTos(dancerService.getAllByType());
    }

    @GetMapping("/search/{city}")
    public List<DancerTo> dancersByNameAndSurname(@PathVariable String city){
        return DancerMapper.mapDancerTos(dancerService.getAllByCity(city));
    }

    @GetMapping("/search")
    public List<DancerTo> dancersByNameAndSurname(@RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "surname", required = false) String surname){
        if (name.isEmpty()){
            return DancerMapper.mapDancerTos(dancerService.getAllBySurname(surname));
        }else if (surname.isEmpty()){
            return DancerMapper.mapDancerTos(dancerService.getAllByName(name));
        }
        return DancerMapper.mapDancerTos(dancerService.getAllByNameAndSurname(name, surname));
    }

    @GetMapping("/{id}")
    public DancerTo get(@PathVariable int id){
        DancerEntity dancerEntity = dancerService.getById(id);
        return  dancerEntity != null ? DancerMapper.mapDancerEntity(dancerEntity) : new DancerTo();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DancerTo save(@RequestBody DancerTo dancerTo){
        return dancerService.save(dancerTo);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "id", required = false) int id,
                             @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) {
            DancerTo dancerTo = get(id);
            if (dancerTo.getImage() != null) {
                imageStorageService.deleteImage(dancerTo.getImage());
            }
            dancerTo.setImage(imageStorageService.uploadImage(file));
            save(dancerTo);
            return "uploaded";
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteFile(@RequestParam(value = "id", required = false) int id){
        DancerTo dancerTo = get(id);
        imageStorageService.deleteImage(dancerTo.getImage());
        dancerTo.setImage(null);
        save(dancerTo);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "id", required = false) int id) {
        DancerTo dancerTo = get(id);
        Resource resource = null;
        if (dancerTo.getImage() != null) {
            resource = imageStorageService.downloadImage(dancerTo.getImage());
            if (resource == null){
                dancerTo.setImage(null);
                save(dancerTo);
            }
        }
        return ResponseEntity.ok()
                .body(resource);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        deleteFile(id);
        dancerService.deleteById(id);
    }

    @GetMapping("/registration")
    public Integer checkSignUp(@RequestParam(value = "email", required = false) String email){

        return dancerService.checkSignUpByEmail(email);
    }

    @GetMapping("/identification")
    public Integer checkSignIn(@RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "password", required = false) String password){
        return dancerService.checkSignInByEmailAndPassword(email, password);
    }

    @PostMapping("/change-password")
    public Boolean changePassword(@RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "password", required = false) String password){
        return dancerService.changePassword(email, password);
    }

    @PostMapping("/change-email")
    public Boolean changeEmail(@RequestParam(value = "oldEmail", required = false) String oldEmail,
                               @RequestParam(value = "newEmail", required = false) String newEmail){
        return dancerService.changeEmail(oldEmail, newEmail);
    }
}
