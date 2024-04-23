package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.controllers.exceptions;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
