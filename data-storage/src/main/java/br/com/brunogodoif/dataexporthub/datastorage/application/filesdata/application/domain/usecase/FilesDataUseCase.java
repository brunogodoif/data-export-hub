package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.usecase;

import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.records.FileDataDTO;

import java.util.List;

public interface FilesDataUseCase {
    List<FileDataDTO> getAllFileData();

    FileDataDTO createFileData(FileDataDTO fileDataDTO);

    FileDataDTO getFileData(String id);

    FileDataDTO updateFileData(String id, FileDataDTO fileDataDTO);

    boolean deleteFileData(String id);
}
