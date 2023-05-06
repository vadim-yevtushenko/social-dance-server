package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.dto.dto.DancerDto;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = DancerRestController.REST_URL)
public class DancerRestController extends BaseController{

    static final String REST_URL = "/dancers";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private DancerService dancerService;

    @GetMapping
    public List<DancerDto> dancers (){
        return dancerService.getAll();
    }

    @GetMapping("/search/{city}")
    public List<DancerDto> dancersByCity(@PathVariable String city){
        return dancerService.getAllByCity(city);
    }

    @GetMapping("/search")
    public List<DancerDto> dancersByNameAndLastName(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "lastName", required = false) String lastName){
        if (name.isEmpty()){
            return dancerService.getAllByLastName(lastName);
        }else if (lastName.isEmpty()){
            return dancerService.getAllByName(name);
        }
        return dancerService.getAllByNameAndLastName(name, lastName);
    }

    @GetMapping("/{id}")
    public DancerDto get(@PathVariable UUID id){
        DancerDto dancer = dancerService.getById(id);
//        if (dancer == null) {
//            throw new NotFoundException("Thee is no dancer with id: " + id.toString());
//        }
        return dancer;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DancerDto save(@RequestBody DancerDto dancerDto){
        return dancerService.save(dancerDto);
    }

    @ResponseBody
    @PostMapping(value = "/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "id", required = false) UUID id,
                             @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) {
            DancerDto dancerDto = get(id);
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
        DancerDto dancerDto = get(id);
        imageStorageService.deleteImage(dancerDto.getImage());
        dancerDto.setImage(null);
        save(dancerDto);
    }

    @GetMapping("/download-image")
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "id", required = false) UUID id) {
        DancerDto dancerDto = get(id);
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


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
//        deleteFile(id);
        dancerService.deleteById(id);
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
