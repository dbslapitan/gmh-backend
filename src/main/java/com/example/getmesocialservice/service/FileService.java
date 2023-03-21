package com.example.getmesocialservice.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {


    private final String BUCKETNAME = "gmh-backend-storage2";
    private final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("AKIA5FL5T6H5L5KXFQCS", "C2wfTR1frhdgOwXXDklkdVrsjC1G50JtjqsZlox2");
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
            .withRegion(Regions.CA_CENTRAL_1)
            .build();

    public boolean upload(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try {

            s3.putObject(BUCKETNAME, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            return true;
        } catch (AmazonServiceException | IOException a) {
            a.printStackTrace();
            return false;
        }
    }

    public S3Object getFile(String key) {

        return s3.getObject(BUCKETNAME, key);
    }

    public void deleteFile(String key){
        s3.deleteObject(BUCKETNAME, key);
    }
}
