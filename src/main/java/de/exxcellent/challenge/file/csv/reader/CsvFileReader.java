package de.exxcellent.challenge.file.csv.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Reads a csv file and creates a CsvContent
 * @see CsvContent
 */
public class CsvFileReader {

    private static final String DELIMITER = ",";

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

        String line;
        for(int rowNo = 0; ((line = br.readLine()) != null); rowNo++){
            var splitValues = valuesToList(line);
            var amountValues = splitValues.size();

            if(amountValues != amountSchemaFields){
                throw new InvalidCsvFormatException(String.format(
                        "The row %s does only have %s values, " +
                        "but the schema of the file has %s fields",
                        rowNo, amountValues, amountSchemaFields));
            }
            currentFileContent.addRow(rowNo, splitValues);
        }
    }

    private List<String> valuesToList(String row){
        var values = row.split(DELIMITER);
        return Arrays.asList(values);
    }
}
