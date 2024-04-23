package br.com.brunogodoif.dataexporthub.datastorage.domain.usecase;

import br.com.brunogodoif.dataexporthub.datastorage.domain.entity.FileData;

public class FilesDataUseCase {
    FileData createFileData(String bucket, String fileName, double size);
    FileData updateFileData(UUID id, String fileName);
    void deleteFileData(UUID id);
}
