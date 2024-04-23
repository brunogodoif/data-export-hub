package br.com.brunogodoif.dataexporthub.datastorage.application.filesdata.application.infrastructure.validator;

import java.io.InputStream;

public interface FileContentValidator {
    void validate(InputStream inputStream);
}
