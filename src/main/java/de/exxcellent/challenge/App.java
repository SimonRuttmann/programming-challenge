package de.exxcellent.challenge;

import de.exxcellent.challenge.algorithms.SmallestGoalSpreadCalculator;
import de.exxcellent.challenge.algorithms.SmallestTemperatureSpreadCalculator;
import de.exxcellent.challenge.dataModel.FootballTeamCollection;
import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;
import de.exxcellent.challenge.file.CsvFootballParser;
import de.exxcellent.challenge.file.CsvWeatherParser;
import de.exxcellent.challenge.file.IParser;

import java.io.IOException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws IOException {

        IParser<MonthlyWeatherReport> weatherReportParser = new CsvWeatherParser();
        var weatherReport = weatherReportParser.parse("src/main/resources/de/exxcellent/challenge/weather.csv");

        IParser<FootballTeamCollection> footballTeamParser = new CsvFootballParser();
        var footballTeams = footballTeamParser.parse("src/main/resources/de/exxcellent/challenge/football.csv");

        String dayWithSmallestTempSpread = new SmallestTemperatureSpreadCalculator().resolveDayWithSmallestTemperatureSpread(weatherReport);
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = new SmallestGoalSpreadCalculator().resolveTeamWithSmallestGoalSpread(footballTeams);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
