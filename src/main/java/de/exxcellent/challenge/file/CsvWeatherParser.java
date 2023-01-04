package de.exxcellent.challenge.file;

/**
 * Impl of IParser, uses CsvFileReader
 */
public class CsvWeatherParser implements IParser{

    private final CsvFileReader reader = new CsvFileReader();
}
