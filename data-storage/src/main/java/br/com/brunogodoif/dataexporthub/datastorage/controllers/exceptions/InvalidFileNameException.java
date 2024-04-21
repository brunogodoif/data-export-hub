package br.com.brunogodoif.dataexporthub.datastorage.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileNameException extends RuntimeException {
    public InvalidFileNameException(String msg) {
        super(msg);
    }
}