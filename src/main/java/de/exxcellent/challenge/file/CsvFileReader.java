package de.exxcellent.challenge.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the file
 */
public class CsvFileReader {

    public CsvContent read(String pathToFile) throws IOException {
        CsvContent fileContent = new CsvContent();

        BufferedReader br = new BufferedReader(new FileReader(pathToFile));

        readSchema(br, fileContent);
        readLines(br, fileContent);

        return fileContent;
    }

    private void readSchema(BufferedReader br, CsvContent fileContent) throws IOException {
        String schemaHeader = br.readLine();
        if(schemaHeader == null) throw new InvalidCsvFormatException("Cannot read csv schema");
    }

    private void readLines(BufferedReader br, CsvContent fileContent){

    }
}
