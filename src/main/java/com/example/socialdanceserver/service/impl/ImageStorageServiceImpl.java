package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String uploadImage(MultipartFile file) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString().replace("-", "");
        String resultFileName = uuidFile + "." + file.getOriginalFilename();


        try (FileOutputStream output = new FileOutputStream(uploadDir + "/" + resultFileName)) {
            output.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFileName;
    }

    @Override
    public Resource downloadImage(String imageName) {
        Path path = Paths.get(uploadPath).toAbsolutePath().normalize();
        Path imagePath = path.resolve(imageName).normalize();
        Resource resource = null;
        if (imagePath.toFile().exists()) {
            try {
                resource = new UrlResource(imagePath.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return resource;
    }

    @Override
    public void deleteImage(String fileName) {
        new Thread(() -> {
            Path path = Paths.get(uploadPath).toAbsolutePath().normalize();
            Path imagePath = path.resolve(fileName).normalize();
            if (imagePath.toFile().exists()) {
                try {
                    Thread.sleep(3000);
                    Files.deleteIfExists(imagePath);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
