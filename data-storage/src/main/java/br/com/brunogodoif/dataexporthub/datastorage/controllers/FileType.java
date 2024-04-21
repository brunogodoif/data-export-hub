package br.com.brunogodoif.dataexporthub.datastorage.controllers;

import br.com.brunogodoif.dataexporthub.datastorage.services.files.validator.FileContentValidator;
import br.com.brunogodoif.dataexporthub.datastorage.services.files.validator.implementation.*;
import lombok.Getter;

@Getter
public enum FileType {
    CSV("csv", new CSVContentValidator()),
    EXCEL("xlsx", new ExcelContentValidator()),
    TSV("tsv", new TSVContentValidator()),
    YML("yml", new YAMLContentValidator()),
    PARQUET("parquet", new ParquetContentValidator()),
    JSON("json", new JSONContentValidator());

    private final String extension;
    private final FileContentValidator contentValidator;

    FileType(String extension, FileContentValidator contentValidator) {
        this.extension = extension;
        this.contentValidator = contentValidator;
    }

    public static boolean isValidExtension(String extension) {
        for (FileType fileType : values()) {
            if (fileType.getExtension().equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static FileType getValidatorByExtensionName(String extension) {
        for (FileType fileType : values()) {
            if (fileType.getExtension().equalsIgnoreCase(extension)) {
                return fileType;
            }
        }
        throw new IllegalArgumentException("Unsupported file extension: " + extension);
    }

}