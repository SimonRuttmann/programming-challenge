package de.exxcellent.challenge.file.csv.parser;

import de.exxcellent.challenge.file.csv.parser.CsvParserException;

import java.util.List;

public class CsvUtil {

    protected static int getIndexOrThrow(List<String> schema, String value){

        var index = schema.indexOf(value);
        if (index == -1){
            throw new CsvParserException(
                    String.format("Key '%s' not present in schema '%s", value, schema));
        }
        return index;
    }
}
