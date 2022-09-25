package com.raytine.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MinioService {
    String update(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException;

    void download(String fileName, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
