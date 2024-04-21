package br.com.brunogodoif.dataexporthub.datastorage.controllers;

import br.com.brunogodoif.dataexporthub.datastorage.controllers.exceptions.*;
import br.com.brunogodoif.dataexporthub.datastorage.services.StringProducerService;
import br.com.brunogodoif.dataexporthub.datastorage.services.files.storage.StorageFileInterface;
import br.com.brunogodoif.dataexporthub.datastorage.services.files.validator.FileContentValidator;
import br.com.brunogodoif.dataexporthub.datastorage.uitls.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class FileStorageController {

    @Autowired
    private StorageFileInterface storageFileService;

    @Autowired
    private StringProducerService producerService;

    @PostMapping("/get-file")
    public ResponseEntity<FileStorageResponse> storeFile(@RequestParam("bucketName") @NotNull String bucketName,
                                                         @RequestParam("fileName") @NotNull String fileName) throws Exception {

        if (bucketName.isEmpty())
            throw new InvalidBucketNameException("Bucket name cannot be null or empty.");

        if (fileName.isEmpty())
            throw new InvalidFileNameException("Bucket name cannot be null or empty.");

        String fileUrl = storageFileService.getFileUrl(bucketName, fileName);
        return ResponseEntity.ok(new FileStorageResponse(fileName, fileUrl));

    }

    @PostMapping("/store-file/{bucketName}")
    public ResponseEntity<FileStorageResponse> storeFile(@PathVariable("bucketName") @NotNull String bucketName,
                                                         @RequestParam("file") @NotNull MultipartFile file) {

        if (bucketName.isEmpty())
            throw new InvalidBucketNameException("Bucket name cannot be null or empty.");

        if (file.isEmpty())
            throw new InvalidFileException("File was not provided in the request.");

        String fileFullName = FileUtil.getFileName(file);
        String fileExtension = FileUtil.getFileExtension(fileFullName);
        boolean validExtension = FileType.isValidExtension(fileExtension);

        if (!validExtension)
            throw new InvalidFileException("Unsupported [" + fileExtension + "] extension found in file [" + fileFullName + "]");

        try {
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            storageFileService.storeFile(bucketName, fileFullName, inputStream, contentType);

            String fileUrl = storageFileService.getFileUrl(bucketName, fileFullName);

            return ResponseEntity.ok(new FileStorageResponse(fileFullName, fileUrl));
        } catch (Exception e) {
            throw new ErrorProcessFileException("Erro ao enviar arquivo para disco configurado");
        }

    }

    @PostMapping("/store-file-to-convert")
    public ResponseEntity<FileStorageResponse> storeFileToConvert(@RequestParam("file") @NotNull MultipartFile file) {

        if (file.isEmpty())
            throw new InvalidFileException("File was not provided in the request.");

        String fileFullName = FileUtil.getFileName(file);
        String fileExtension = FileUtil.getFileExtension(fileFullName);
        boolean validExtension = FileType.isValidExtension(fileExtension);

        if (!validExtension)
            throw new InvalidFileException("Unsupported [" + fileExtension + "] extension found in file [" + fileFullName + "]");

        validateFileContent(file);
        String idRequest = UUID.randomUUID().toString();

        try {
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            storageFileService.storeFile(idRequest, fileFullName, inputStream, contentType);
            String fileUrl = storageFileService.getFileUrl(idRequest, fileFullName);
            //producerService.sendMessage(idRequest, UUID.randomUUID().toString(), "EsteH-eader", "Valor do Header");
            return ResponseEntity.ok(new FileStorageResponse(idRequest, fileUrl));
        } catch (Exception e) {
            throw new ErrorProcessFileException("Erro ao enviar arquivo para disco configurado");
        }

    }

    private void validateFileContent(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            FileType fileType = FileType.getValidatorByExtensionName(FileUtil.getFileExtension(file));
            FileContentValidator validator = fileType.getContentValidator();
            validator.validate(inputStream);
        } catch (IOException e) {
            throw new ErrorReadFileException("Error reading file content.");
        }
    }
}