package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.controllers;

import br.com.brunogodoif.dataexporthub.datastorage.domain.entity.FileData;
import br.com.brunogodoif.dataexporthub.datastorage.services.files.FilesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("files")
public class FilesDataController {

    @Autowired
    private FilesDataService filesDataService;

    @GetMapping
    public ResponseEntity<List<FileData>> listarProdutos() {
        List<FileData> fileData = filesDataService.findAll();
        return new ResponseEntity<>(fileData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileData> encontrarProdutoPorId(@PathVariable String id) {
        FileData fileData = filesDataService.findById(id);
        if (fileData != null) {
            return new ResponseEntity<>(fileData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FileData> criarProduto(@RequestBody FileData fileData) {
        FileData novoFileData = filesDataService.save(fileData);
        return new ResponseEntity<>(novoFileData, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileData> atualizarProduto(@PathVariable String id, @RequestBody FileData fileDataAtualizado) {
        FileData fileDataExistente = filesDataService.findById(id);
        if (fileDataExistente != null) {
            fileDataAtualizado.setId(id);
            FileData fileDataAtualizadoSalvo = filesDataService.save(fileDataAtualizado);
            return new ResponseEntity<>(fileDataAtualizadoSalvo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable String id) {
        FileData fileDataExistente = filesDataService.findById(id);
        if (fileDataExistente != null) {
            filesDataService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}