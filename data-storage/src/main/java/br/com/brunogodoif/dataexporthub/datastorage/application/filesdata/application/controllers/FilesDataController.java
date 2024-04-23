package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers;

import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.records.FileDataDTO;
import br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.domain.usecase.FilesDataUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("files")
public class FilesDataController {

    @Autowired
    private final FilesDataUseCase fileDataUseCase;

    public FilesDataController(FilesDataUseCase fileDataUseCase) {
        this.fileDataUseCase = fileDataUseCase;
    }

    @GetMapping
    public ResponseEntity<List<FileDataDTO>> getAllFileData() {
        List<FileDataDTO> allFileData = fileDataUseCase.getAllFileData();
        return new ResponseEntity<>(allFileData, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FileDataDTO> createFileData(@RequestBody FileDataDTO fileDataDTO) {
        FileDataDTO createdFileData = fileDataUseCase.createFileData(fileDataDTO);
        return new ResponseEntity<>(createdFileData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDataDTO> getFileData(@PathVariable String id) {
        FileDataDTO fileDataDTO = fileDataUseCase.getFileData(id);
        if (fileDataDTO != null) {
            return new ResponseEntity<>(fileDataDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileDataDTO> updateFileData(@PathVariable String id, @RequestBody FileDataDTO fileDataDTO) {
        FileDataDTO updatedFileData = fileDataUseCase.updateFileData(id, fileDataDTO);
        if (updatedFileData != null) {
            return new ResponseEntity<>(updatedFileData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFileData(@PathVariable String id) {
        boolean deleted = fileDataUseCase.deleteFileData(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}