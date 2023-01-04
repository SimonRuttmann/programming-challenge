package de.exxcellent.challenge;

import de.exxcellent.challenge.dataModel.DailyWeatherReport;
import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;
import de.exxcellent.challenge.file.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final List<String> schemaWeather = Arrays.asList("Day","MxT","MnT","AvT","AvDP","1HrP TPcpn","PDir","AvSp","Dir","MxS","SkyC","MxR","Mn","R AvSLP");
    private final List<String> firstWeatherRow = Arrays.asList("1","88","59","74","53.8","0","280","9.6","270","17","1.6","93","23","1004.5");

    @Test
    void CsvReaderTestHappyPath() throws IOException {
        var weatherContent = (CsvContent) new CsvFileReader().read("src/test/resources/csv/weather_valid.csv");

        assertEquals(weatherContent.getSchema(), schemaWeather, "Schema does not match");
        assertEquals(weatherContent.getRows().get(0), firstWeatherRow);
    }

    @Test
    void CsvReaderTestExceptionPath(){
        assertThrows(InvalidCsvFormatException.class,
                () -> new CsvFileReader().read("src/test/resources/csv/weather_invalid.csv"),
                "Value on row 2 is missing, but no exception thrown");
    }

    @Test
    void CsVMapperTestHappyPath() throws IOException {

        var rawCsvContent = new CsvContent();
        rawCsvContent.setSchema(schemaWeather);
        rawCsvContent.addRow(0, firstWeatherRow);

        CsvWeatherParser weatherParserMock = new CsvWeatherParser(){
            public MonthlyWeatherReport parse(String dummy){
                return this.mapToClass(rawCsvContent);
            }
        };

        var parsedWeatherReport = weatherParserMock.parse("").getDailyWeatherReports().get(0);

        var correctWeatherReport = new DailyWeatherReport(1, 88, 59);

        assertEquals(correctWeatherReport.getDay(), parsedWeatherReport.getDay());
        assertEquals(correctWeatherReport.getMaxTemperature(), parsedWeatherReport.getMaxTemperature());
        assertEquals(correctWeatherReport.getMinTemperature(), parsedWeatherReport.getMinTemperature());
    }

    @Test
    void CsVMapperTestExceptionPath() {

        var rawCsvContent = new CsvContent();
        var invalidWeatherSchema = schemaWeather.stream().filter(schemaEntry -> schemaEntry.equals("MnT")).collect(Collectors.toList());

        rawCsvContent.setSchema(invalidWeatherSchema);
        rawCsvContent.addRow(0, firstWeatherRow);

        CsvWeatherParser weatherParserMock = new CsvWeatherParser(){
            public MonthlyWeatherReport parse(String dummy){
                return this.mapToClass(rawCsvContent);
            }
        };

        assertThrows(CsvParserException.class, () ->  weatherParserMock.parse(""));
    }

}