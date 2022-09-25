package com.raytine.controller;

import com.raytine.service.MinioService;
import com.raytine.vo.Resp;
import io.minio.errors.MinioException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/oss")
public class OssController extends BaseController{

    private final MinioService minioService;

    public OssController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/uploadObj")
    public Resp<Void> upload(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        minioService.update(file);
        return success(null);
    }
}
