package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileDataDTO {
    // Getters e Setters
    private String id;
    private String bucket;
    private String fileName;
    private double size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}