package de.exxcellent.challenge.algorithms;

import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;

import java.util.stream.Collectors;

public class SmallestTemperatureSpreadCalculator {

    /**
     * Calculates the minimum of (MxT-MnT) for each DailyWeatherReport and returns the day of this weather report
     */
    public String resolveDayWithSmallestTemperatureSpread(MonthlyWeatherReport monthlyWeatherReport){

        var weatherMinMaxTempPairs = monthlyWeatherReport.getDailyWeatherReports().stream().
                map(weather -> new Pair<>(weather.getMinTemperature(), weather.getMaxTemperature())).
                collect(Collectors.toList());

        var resultIndex = new MinimumDifference().calcMinimumDifference(weatherMinMaxTempPairs);

        return String.valueOf(monthlyWeatherReport.getDailyWeatherReports().get(resultIndex).getDay());
    }
}
