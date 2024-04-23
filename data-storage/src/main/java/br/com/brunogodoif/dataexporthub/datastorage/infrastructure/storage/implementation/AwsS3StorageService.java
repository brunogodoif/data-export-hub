package br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.implementation;

import br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.StorageFileInterface;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

@Service
public class AwsS3StorageService implements StorageFileInterface {
    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public boolean storeFile(String bucketName, String fileName, InputStream inputStream, String contentType) throws Exception {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentDisposition("attachment; filename=\"" + fileName + "\"");
            metadata.setContentType(contentType);

            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));

            return true;

        } catch (Exception e) {
            throw new Exception("Erro ao fazer upload do arquivo para o AWS S3", e);
        }
    }

    @Override
    public String getFileUrl(String bucketName, String fileName) throws Exception {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName);
        URL url = amazonS3.generatePresignedUrl(request);
        return url.toString();
    }
}
