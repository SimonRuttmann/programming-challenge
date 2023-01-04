package de.exxcellent.challenge.file;

import java.io.IOException;

public abstract class AbstractCsvParser<T> implements IParser<T>{

    @Override
    public T parse(String pathToFile) throws IOException {

        CsvFileReader reader = new CsvFileReader();
        var result = reader.read(pathToFile);

        return mapToClass(result);
    }

    protected abstract T mapToClass(CsvContent csvContent);
}
