package de.exxcellent.challenge.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Reads the file
 */
public class CsvFileReader {

    private static final String DELIMITER = ";";

    public CsvContent read(String pathToFile) throws IOException {
        CsvContent fileContent = new CsvContent();

        BufferedReader br = new BufferedReader(new FileReader(pathToFile));

        readSchema(br, fileContent);
        readLines(br, fileContent);

        return fileContent;
    }

    private void readSchema(BufferedReader br, CsvContent fileContent) throws IOException {
        String schemaHeader = br.readLine();
        if(schemaHeader == null) {
            throw new InvalidCsvFormatException("Cannot read csv schema");
        }
        var headerRecord = valuesToList(schemaHeader);
        fileContent.setSchema(headerRecord);
    }

    private void readLines(BufferedReader br, CsvContent currentFileContent) throws IOException {

        int amountSchemaFields = currentFileContent.getSchema().size();
        int row = 0;

        String line;
        while((line = br.readLine()) != null){
            var splitValues = valuesToList(line);
            var amountValues = splitValues.size();
            row++;

            if(amountValues != amountSchemaFields){
                throw new InvalidCsvFormatException("Not enough values on this line");
            }
            currentFileContent.addRow(0, splitValues);
        }
    }

    private List<String> valuesToList(String row){
        var values = row.split(DELIMITER);
        return Arrays.asList(values);
    }
}
