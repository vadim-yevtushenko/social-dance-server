package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.dto.DancerDto;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.ImageStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = DancerRestController.REST_URL)
public class DancerRestController extends BaseController{

    static final String REST_URL = "/dancers";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private DancerService dancerService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<DancerDto> getAllDancers (){
        log.info("Get all dancers");
        return dancerService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/search/{city}")
    public List<DancerDto> getDancersByCity(@PathVariable String city){
        log.info("Get all dancers by city: {}", city);
        return dancerService.getAllByCity(city);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/search")
    public List<DancerDto> getDancersByNameAndLastName(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "lastName", required = false) String lastName){
        log.info("Get all dancers by name: {} and lastName: {}", name, lastName);
        if (name.isEmpty()){
            return dancerService.getAllByLastName(lastName);
        }else if (lastName.isEmpty()){
            return dancerService.getAllByName(name);
        }
        return dancerService.getAllByNameAndLastName(name, lastName);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public DancerDto getById(@PathVariable UUID id){
        log.info("Get dancer by uuid: {}", id);
        DancerDto dancer = dancerService.getById(id);
        validateFound(dancer, DancerDto.class, id);

        return dancer;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DancerDto save(@RequestBody DancerDto dancerDto){
        if (dancerDto.getId() != null){
            log.info("Update dancer with uuid: {}", dancerDto.getId());
        } else {
            log.info("Create new dancer");
        }

        return dancerService.save(dancerDto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        log.info("Delete dancer with uuid: {}", id);
//        deleteFile(id);
        dancerService.deleteById(id);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "id", required = false) UUID id,
                             @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) {
            DancerDto dancerDto = getById(id);
            if (dancerDto.getImage() != null) {
                imageStorageService.deleteImage(dancerDto.getImage());
            }
            dancerDto.setImage(imageStorageService.uploadImage(file));
            save(dancerDto);
            return "uploaded";
        }
        return "not uploaded";
    }

    @DeleteMapping("/delete-image")
    public void deleteFile(@RequestParam(value = "id", required = false) UUID id){
        DancerDto dancerDto = getById(id);
        imageStorageService.deleteImage(dancerDto.getImage());
        dancerDto.setImage(null);
        save(dancerDto);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "id", required = false) UUID id) {
        DancerDto dancerDto = getById(id);
        Resource resource = null;
        if (dancerDto.getImage() != null) {
            resource = imageStorageService.downloadImage(dancerDto.getImage());
            if (resource == null){
                dancerDto.setImage(null);
                save(dancerDto);
            }
        }
        return ResponseEntity.ok()
                .body(resource);
    }


//    @GetMapping("/registration")
//    public Integer checkSignUp(@RequestParam(value = "email", required = false) String email){
//
//        return dancerService.checkSignUpByEmail(email);
//    }
//
//    @GetMapping("/identification")
//    public Integer checkSignIn(@RequestParam(value = "email", required = false) String email,
//                               @RequestParam(value = "password", required = false) String password){
//        return dancerService.checkSignInByEmailAndPassword(email, password);
//    }
//
//    @PostMapping("/change-password")
//    public Boolean changePassword(@RequestParam(value = "email", required = false) String email,
//                                  @RequestParam(value = "password", required = false) String password){
//        return dancerService.changePassword(email, password);
//    }
//
//    @PostMapping("/change-email")
//    public Boolean changeEmail(@RequestParam(value = "oldEmail", required = false) String oldEmail,
//                               @RequestParam(value = "newEmail", required = false) String newEmail){
//        return dancerService.changeEmail(oldEmail, newEmail);
//    }
}
