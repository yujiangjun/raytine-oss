package com.raytine.service.impl;

import com.raytine.config.MinioConfigProperties;
import com.raytine.service.MinioService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;
    private final MinioConfigProperties minioConfigProperties;

    public MinioServiceImpl(MinioClient minioClient, MinioConfigProperties minioConfigProperties) {
        this.minioClient = minioClient;
        this.minioConfigProperties = minioConfigProperties;
    }

    @Override
    public String update(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {

        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfigProperties.getBucketName()).build());
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
        stream.close();

        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                .builder()
                .bucket(minioConfigProperties.getBucketName())
                .object(file.getOriginalFilename())
                .method(Method.GET)
                .build());
    }

    @Override
    public void download(String fileName, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.downloadObject(DownloadObjectArgs.builder()
                        .bucket(minioConfigProperties.getBucketName())
                        .object(fileName)
                        .filename(fileName)
                .build());
    }

    @Override
    public String getUrl(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                .builder()
                .bucket(minioConfigProperties.getBucketName())
                .object(fileName)
                .method(Method.GET)
                .build());
    }
}
