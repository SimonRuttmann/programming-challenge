package de.exxcellent.challenge.file.csv.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object representation of a csv file
 */
public class CsvContent {

    private List<String> schema;

    private final Map<Integer, List<String>> rows = new HashMap<>();

    // Getters an Setter

    public List<String> getSchema() {
        return schema;
    }

    public void setSchema(List<String> schema ){
        this.schema = schema;
    }

    public Map<Integer, List<String>> getRows(){
        return rows;
    }

    public void addRow(int row, List<String> record){
        this.rows.put(row, record);
    }
}
