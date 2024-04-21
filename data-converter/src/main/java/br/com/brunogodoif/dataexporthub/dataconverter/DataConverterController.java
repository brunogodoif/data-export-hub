package br.com.brunogodoif.dataexporthub.dataconverter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DataConverterController {


    @GetMapping("/convert/{requestId}/{fileType}")
    public ResponseEntity<String> convertData(@PathVariable UUID requestId, @PathVariable String fileType) {
        return ResponseEntity.accepted().body("Enviado para processamento");
    }

    @GetMapping("/status/{requestId}/{fileType}")
    public ResponseEntity<String> getRequestStatus(@PathVariable UUID requestId, @PathVariable String fileType) {
        return ResponseEntity.ok().body("Solicitação em processamento");
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requisição não encontrada");
    }
}


