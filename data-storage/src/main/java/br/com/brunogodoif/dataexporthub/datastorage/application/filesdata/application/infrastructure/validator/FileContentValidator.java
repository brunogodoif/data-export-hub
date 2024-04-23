package br.com.brunogodoif.dataexporthub.datastorage.services.files.validator;

import java.io.InputStream;

public interface FileContentValidator {
    void validate(InputStream inputStream);
}
