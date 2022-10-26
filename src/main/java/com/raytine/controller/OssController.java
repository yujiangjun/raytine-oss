package com.raytine.controller;

import com.raytine.service.MinioService;
import com.raytine.vo.Resp;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    public Resp<String> upload(MultipartFile file) throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        return success(minioService.update(file));
    }

    @GetMapping("/getUrl")
    public Resp<String> getUrlList(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return success(minioService.getUrl(fileName));
    }
    @GetMapping("/download")
    public Resp<Void> download(String fileName, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioService.download(fileName,response);
        return success(null);
    }
}
