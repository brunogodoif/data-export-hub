package br.com.brunogodoif.dataexporthub.datastorage.infrastructure.storage;

import java.io.InputStream;

public interface StorageFileInterface {

    boolean storeFile(String bucketName, String fileName, InputStream inputStream, String contentType) throws Exception;

    String getFileUrl(String bucketName, String fileName) throws Exception;
}
