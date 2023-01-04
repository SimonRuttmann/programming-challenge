package de.exxcellent.challenge;

import de.exxcellent.challenge.algorithms.MinimumDifference;
import de.exxcellent.challenge.file.csv.parser.CsvFootballParser;
import de.exxcellent.challenge.file.csv.parser.CsvParserException;
import de.exxcellent.challenge.file.csv.parser.CsvWeatherParser;
import de.exxcellent.challenge.file.csv.reader.CsvContent;
import de.exxcellent.challenge.file.csv.reader.CsvFileReader;
import de.exxcellent.challenge.file.csv.reader.InvalidCsvFormatException;
import de.exxcellent.challenge.toolbox.Pair;
import de.exxcellent.challenge.calculator.SmallestGoalSpreadCalculator;
import de.exxcellent.challenge.calculator.SmallestTemperatureSpreadCalculator;
import de.exxcellent.challenge.dataModel.weather.DailyWeatherReport;
import de.exxcellent.challenge.dataModel.football.FootballTeam;
import de.exxcellent.challenge.dataModel.football.FootballTeamCollection;
import de.exxcellent.challenge.dataModel.weather.MonthlyWeatherReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
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

    private final List<String> schemaFootball = Arrays.asList("Team","Games","Wins","Losses","Draws","Goals","Goals Allowed","Points");
    private final List<String> firstFootballRow = Arrays.asList("Arsenal","38","26","9","3","79","36","87");


    // CsvReader tests

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


    // CsvWeatherParser mapping tests

    @Test
    void CsVMapperTestWeatherHappyPath() throws IOException {

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
    void CsVMapperTestWeatherExceptionPath() {

        var rawCsvContent = new CsvContent();
        var invalidWeatherSchema = schemaWeather.stream().
                filter(schemaEntry -> !schemaEntry.equals("MnT")).
                collect(Collectors.toList());

        rawCsvContent.setSchema(invalidWeatherSchema);
        rawCsvContent.addRow(0, firstWeatherRow);

        CsvWeatherParser weatherParserMock = new CsvWeatherParser(){
            public MonthlyWeatherReport parse(String dummy){
                return this.mapToClass(rawCsvContent);
            }
        };

        assertThrows(CsvParserException.class, () ->  weatherParserMock.parse(""));
    }

    // CsvFootballParser mapping tests

    @Test
    void CsVMapperTestFootballHappyPath() throws IOException {

        var rawCsvContent = new CsvContent();
        rawCsvContent.setSchema(schemaFootball);
        rawCsvContent.addRow(0, firstFootballRow);

        CsvFootballParser footballParserMock = new CsvFootballParser(){
            public FootballTeamCollection parse(String dummy){
                return this.mapToClass(rawCsvContent);
            }
        };

        var parsedFootballTeam = footballParserMock.parse("").getFootballTeams().get(0);

        var correctFootballTeam = new FootballTeam("Arsenal", 79, 36);

        assertEquals(correctFootballTeam.getTeam(), parsedFootballTeam.getTeam());
        assertEquals(correctFootballTeam.getGoalsAllowed(), parsedFootballTeam.getGoalsAllowed());
        assertEquals(correctFootballTeam.getGoals(), parsedFootballTeam.getGoals());
    }


    @Test
    void CsVMapperTestFootballExceptionPath() {

        var rawCsvContent = new CsvContent();
        var invalidFootballSchema = schemaFootball.stream().
                filter(footballSchema -> !footballSchema.equals("Goals Allowed")).
                collect(Collectors.toList());

        rawCsvContent.setSchema(invalidFootballSchema);
        rawCsvContent.addRow(0, firstFootballRow);

        CsvFootballParser footballParserMock = new CsvFootballParser(){
            public FootballTeamCollection parse(String dummy){
                return this.mapToClass(rawCsvContent);
            }
        };

        assertThrows(CsvParserException.class, () ->  footballParserMock.parse(""));
    }


    //Minimum-Difference tests

    @Test
    void MinimumDifferenceAlgorithmTestHappyPath() {
        var somePairs =  Arrays.asList(new Pair<>(5.3,8.1f),new Pair<>(1, 2),new Pair<>(1,7.3f));
        assertEquals(1, new MinimumDifference().calcMinimumDifference(somePairs));

        somePairs =  Arrays.asList(new Pair<>(6.0,6.0),new Pair<>(1,1) ,new Pair<>(1,10));
        assertEquals(0, new MinimumDifference().calcMinimumDifference(somePairs));
    }

    @Test
    void MinimumDifferenceAlgorithmTestExceptionPath(){
        var emptyPairs = new ArrayList<Pair<Integer, Integer>>();
        assertThrows(IllegalArgumentException.class,
                () -> new MinimumDifference().calcMinimumDifference(emptyPairs));
    }


    // Calculator tests

    @Test
    void SmallestTemperatureSpreadTest(){
        var weatherReports = new MonthlyWeatherReport();
        weatherReports.addDailyWeatherReport(new DailyWeatherReport(1,1,10));
        weatherReports.addDailyWeatherReport(new DailyWeatherReport(2,2,3)); //Diff = 1
        weatherReports.addDailyWeatherReport(new DailyWeatherReport(3,3,5));

        var res = new SmallestTemperatureSpreadCalculator().resolveDayWithSmallestTemperatureSpread(weatherReports);
        assertEquals(String.valueOf(2), res);
    }

    @Test
    void SmallestGoalSpreadCalculatorTest(){

        var footballTeams = new FootballTeamCollection();
        footballTeams.addFootballTeam(new FootballTeam("A", 1, 3));
        footballTeams.addFootballTeam(new FootballTeam("B", 2, 5));
        footballTeams.addFootballTeam(new FootballTeam("C", 5, 4)); //Diff = 1

        var res = new SmallestGoalSpreadCalculator().resolveTeamWithSmallestGoalSpread(footballTeams);
        assertEquals("C", res);
    }
}