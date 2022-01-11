package com.example.socialdanceserver.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface ImageStorageService {

    String uploadImage(MultipartFile file);

    Resource downloadImage(String imageName);

    void deleteImage(String imageName);
}