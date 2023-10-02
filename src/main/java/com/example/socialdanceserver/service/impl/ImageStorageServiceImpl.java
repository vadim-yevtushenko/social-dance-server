package com.example.socialdanceserver.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.example.socialdanceserver.config.properties.AwsProperties;
import com.example.socialdanceserver.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Autowired
    private AwsProperties awsProperties;

    @Value("${s3.bucket-name}")
    private String bucketName;

    private final String IMAGES_FOLDER = "images/";

    @Override
    public String uploadImage(MultipartFile file) {

        AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getKey(), awsProperties.getSecretKey());

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();

        String uuidFile = UUID.randomUUID().toString().replace("-", "");
        String resultFileName = uuidFile + ".jpeg";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setUserMetadata(Map.of("Content-Type", "image/jpeg"));
        try {
            s3client.putObject(
                    bucketName,
                    IMAGES_FOLDER + resultFileName,
                    file.getInputStream(),
                    objectMetadata);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        URL url = s3client.generatePresignedUrl(bucketName, IMAGES_FOLDER + resultFileName, new Date(10));

        return url.getProtocol() + "://" + url.getHost() + url.getPath();
    }

    @Override
    public void deleteImage(String url) {
        AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getKey(), awsProperties.getSecretKey());

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();

        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];

        s3client.deleteObject(bucketName, IMAGES_FOLDER + fileName);

    }
}
