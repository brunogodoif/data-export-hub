package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String msg) {
        super(msg);
    }
}