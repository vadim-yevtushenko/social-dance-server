package com.example.socialdanceserver.service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String uploadImage(MultipartFile file);

    void deleteImage(String imageName);

}