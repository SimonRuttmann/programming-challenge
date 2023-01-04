package de.exxcellent.challenge.file;

import de.exxcellent.challenge.dataModel.DailyWeatherReport;
import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;

import static de.exxcellent.challenge.file.CsvUtil.getIndexOrThrow;

/**
 * Impl of IParser, uses CsvFileReader
 */
public class CsvWeatherParser extends AbstractCsvParser<MonthlyWeatherReport> {

    protected MonthlyWeatherReport mapToClass(CsvContent csvContent){

        var monthlyWeatherReport = new MonthlyWeatherReport();

        var dayIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.WeatherConstants.DAY);
        var maxTempIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.WeatherConstants.MAX_TEMPERATURE);
        var minTempIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.WeatherConstants.MIN_TEMPERATURE);

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

}
