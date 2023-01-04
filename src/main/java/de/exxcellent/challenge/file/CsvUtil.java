package de.exxcellent.challenge.file;

import java.util.List;

public class CsvUtil {

    protected static int getIndexOrTrow(List<String> schema, String value){

        var index = schema.indexOf(value);
        if (index == -1){
            throw new CsvParserException(
                    String.format("Key '%s' not present in schema '%s", value, schema));
        }
        return index;
    }
}
