package com.raytine;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class OssTest {
    @Test
    public void test01() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
            try {
                // Create a minioClient with the MinIO server playground, its access key and secret key.
                MinioClient minioClient =
                        MinioClient.builder()
                                .endpoint("http://8.134.34.84:9090")
                                .credentials("VE8XCF0A2ALLO8AYYS37", "4sHvo04dLlt6sANKYDKGzJ8w5PHHeQgfmxbKo+Hp")
                                .build();

                // Make 'asiatrip' bucket if not exist.
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("raytine-im").build());
                if (!found) {
                    // Make a new bucket called 'asiatrip'.
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket("raytine-im").build());
                } else {
                    System.out.println("Bucket 'public' already exists.");
                }

                // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
                // 'asiatrip'.
                FileInputStream fileInputStream = new FileInputStream("d:/2.k8s-control-plane-slave安装说明.md");
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket("raytine-im")
                        .object("2.k8s-control-plane-slave安装说明.md")
                        .stream(fileInputStream,fileInputStream.available(),0)
                        .contentType("application/octet-stream")
                        .build());
//                minioClient.uploadObject(
//                        UploadObjectArgs.builder()
//                                .bucket("raytine-im")
//                                .object("1.k8s-control-plane-master.md")
//                                .filename("d:/1.k8s-control-plane-master.md")
//                                .build());
                System.out.println("upload complete");
            } catch (MinioException e) {
                System.out.println("Error occurred: " + e);
                System.out.println("HTTP trace: " + e.httpTrace());
            }


    }
}
