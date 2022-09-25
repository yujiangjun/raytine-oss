package com.raytine.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(@Autowired MinioConfigProperties minioConfigProperties){
        return MinioClient.builder()
                        .endpoint(minioConfigProperties.getEndPoint())
                        .credentials(minioConfigProperties.getAccessKey(), minioConfigProperties.getSecretKey())
                        .build();
    }
}
