package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StoreFileException extends RuntimeException {
    public StoreFileException(String msg) {
        super(msg);
    }
}