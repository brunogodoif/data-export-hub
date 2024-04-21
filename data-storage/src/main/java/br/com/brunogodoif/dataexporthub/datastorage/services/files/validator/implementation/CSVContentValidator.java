package br.com.brunogodoif.dataexporthub.datastorage.services.files.validator.implementation;

import br.com.brunogodoif.dataexporthub.datastorage.controllers.exceptions.ErrorReadFileException;
import br.com.brunogodoif.dataexporthub.datastorage.controllers.exceptions.InvalidFileException;
import br.com.brunogodoif.dataexporthub.datastorage.services.files.validator.FileContentValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CSVContentValidator implements FileContentValidator {
    @Override
    public void validate(InputStream inputStream) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String firstLine = br.readLine();
            if (firstLine == null)
                throw new InvalidFileException("The CSV file is empty.");

            String headerLine = firstLine.trim();
            if (headerLine.isEmpty())
                throw new InvalidFileException("The CSV file does not contain a header in the first line.");

            int lineCount = 1;

            while (br.readLine() != null) {
                lineCount++;
            }

            if (lineCount < 2)
                throw new InvalidFileException("The CSV file contains no data other than the header.");

            br.close();
        } catch (IOException e) {
            throw new ErrorReadFileException("Error processing CSV file");
        }
    }

}
