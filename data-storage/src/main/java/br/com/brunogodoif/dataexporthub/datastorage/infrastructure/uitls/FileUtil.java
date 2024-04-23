package br.com.brunogodoif.dataexporthub.datastorage.infrastructure.uitls;

import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.exceptions.InvalidFileException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static String getFileName(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new InvalidFileException("File is empty.");

        return file.getOriginalFilename();
    }

    public static String getFileNameWithoutExtension(MultipartFile file) {
        if (file == null || file.isEmpty())
            throw new InvalidFileException("File is empty.");

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null)
            throw new InvalidFileException("File has no original filename.");

        Path path = Paths.get(originalFilename);

        return path.getFileName().toString().replaceFirst("[.][^.]+$", "");
    }

    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            int lastIndex = fileName.lastIndexOf('.');
            if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
                return fileName.substring(lastIndex + 1).toLowerCase();
            }
        }
        return "";
    }

    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex + 1).toLowerCase();
        }
        return "";
    }

}
