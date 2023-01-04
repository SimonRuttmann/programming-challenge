package de.exxcellent.challenge.file;

import de.exxcellent.challenge.dataModel.DailyWeatherReport;
import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;

import java.io.IOException;
import java.util.List;

/**
 * Impl of IParser, uses CsvFileReader
 */
public class CsvWeatherParser implements IParser<MonthlyWeatherReport> {

    @Override
    public MonthlyWeatherReport parse(String pathToFile) throws IOException {

        CsvFileReader reader = new CsvFileReader();
        var result = reader.read(pathToFile);

        return mapToClass(result);
    }

    //Protected to reference in tests
    protected MonthlyWeatherReport mapToClass(CsvContent csvContent){

        var monthlyWeatherReport = new MonthlyWeatherReport();

        var dayIndex = getIndexOrTrow(csvContent.getSchema(), CsvConstants.DAY);
        var maxTempIndex = getIndexOrTrow(csvContent.getSchema(), CsvConstants.MAX_TEMPERATURE);
        var minTempIndex = getIndexOrTrow(csvContent.getSchema(), CsvConstants.MIN_TEMPERATURE);

        for (var record : csvContent.getRows().entrySet()){
            var day = record.getValue().get(dayIndex);
            var maxTemp = record.getValue().get(maxTempIndex);
            var minTemp = record.getValue().get(minTempIndex);

            var dailyReport = new DailyWeatherReport(
                    Integer.parseInt(day), Integer.parseInt(maxTemp), Integer.parseInt(minTemp));
            monthlyWeatherReport.addDailyWeatherReport(dailyReport);
        }

        return monthlyWeatherReport;
    }

    private int getIndexOrTrow(List<String> schema, String value){

        var index = schema.indexOf(value);
        if (index == -1){
            throw new CsvParserException(
                    String.format("Key '%s' not present in schema '%s", value, schema));
        }
        return index;
    }
}
