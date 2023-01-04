package de.exxcellent.challenge.file;

import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;

/**
 * Impl of IParser, uses CsvFileReader
 */
public class CsvWeatherParser implements IParser<MonthlyWeatherReport> {

    private final CsvFileReader reader = new CsvFileReader();

    @Override
    public MonthlyWeatherReport parse(String pathToFile) {
        return null;
    }

    private MonthlyWeatherReport mapToClass(){
        return null;
    }
}
