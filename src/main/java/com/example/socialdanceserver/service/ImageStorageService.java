package com.example.socialdanceserver.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    String uploadImage(MultipartFile file) throws IOException;

    void deleteImage(String imageName);
}