package de.exxcellent.challenge.file.csv.parser;

import de.exxcellent.challenge.file.IParser;
import de.exxcellent.challenge.file.csv.reader.CsvFileReader;
import de.exxcellent.challenge.file.csv.reader.CsvContent;

import java.io.IOException;

public abstract class AbstractCsvParser<T> implements IParser<T> {

    @Override
    public T parse(String pathToFile) throws IOException {

        CsvFileReader reader = new CsvFileReader();
        var result = reader.read(pathToFile);

        return mapToClass(result);
    }

    protected abstract T mapToClass(CsvContent csvContent);
}
