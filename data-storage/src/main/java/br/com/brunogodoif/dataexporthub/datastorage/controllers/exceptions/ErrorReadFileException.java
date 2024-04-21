package br.com.brunogodoif.dataexporthub.datastorage.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorReadFileException extends RuntimeException {
    public ErrorReadFileException(String msg) {
        super(msg);
    }
}