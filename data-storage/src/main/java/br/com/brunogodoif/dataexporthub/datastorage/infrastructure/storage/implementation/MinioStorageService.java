package br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.implementation;

import br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.StorageFileInterface;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MinioStorageService implements StorageFileInterface {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    private MinioClient createMinioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public boolean storeFile(String bucketName, String fileName, InputStream inputStream, String contentType) throws Exception {
        try {
            MinioClient minioClient = createMinioClient();

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists)
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream, -1, 10485760)
                    .contentType(contentType)
                    .build());

            return true;

        } catch (Exception e) {
            throw new Exception("Erro ao fazer upload do arquivo para o MinIO", e);
        }
    }

    @Override
    public String getFileUrl(String bucketName, String fileName) throws Exception {
        try {
            MinioClient minioClient = createMinioClient();

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(7 * 24 * 3600)
                            .build());

            return url;
        } catch (MinioException | IOException e) {
            throw new Exception("Erro ao gerar a URL do arquivo no MinIO", e);
        }
    }
}
