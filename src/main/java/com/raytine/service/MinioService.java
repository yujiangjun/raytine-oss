package com.raytine.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MinioService {
    void update(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException;
}
