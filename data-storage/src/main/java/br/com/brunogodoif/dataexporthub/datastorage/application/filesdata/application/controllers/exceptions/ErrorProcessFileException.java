package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorProcessFileException extends RuntimeException {
    public ErrorProcessFileException(String msg) {
        super(msg);
    }
}