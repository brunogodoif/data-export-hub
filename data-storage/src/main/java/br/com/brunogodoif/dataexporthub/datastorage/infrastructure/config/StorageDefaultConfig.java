package br.com.brunogodoif.dataexporthub.datastorage.config;

import br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.StorageFileInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StorageDefaultConfig {
    @Value("${storage.type:minio}")
    private String storageType;

    @Bean
    @Primary
    public StorageFileInterface storageFileService(
            @Qualifier("minioStorageService") StorageFileInterface minioStorageService,
            @Qualifier("awsS3StorageService") StorageFileInterface awsS3StorageService) {

        if ("awsS3".equals(storageType))
            return awsS3StorageService;

        return minioStorageService;
    }
}
