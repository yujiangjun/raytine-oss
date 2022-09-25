package com.raytine.service.impl;

import com.raytine.config.MinioConfigProperties;
import com.raytine.service.MinioService;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioServiceImpl implements MinioService {
    private MinioClient minioClient;
    private MinioConfigProperties minioConfigProperties;

    public MinioServiceImpl(MinioClient minioClient, MinioConfigProperties minioConfigProperties) {
        this.minioClient = minioClient;
        this.minioConfigProperties = minioConfigProperties;
    }

    @Override
    public void update(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("raytine-im").build());
        if (!found) {
            throw new MinioException("bucket 不存在");
        }
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioConfigProperties.getBucketName())
                .object(file.getOriginalFilename())
                .stream(stream,file.getSize(),0)
                .contentType("application/octet-stream")
                .build());
    }
}
