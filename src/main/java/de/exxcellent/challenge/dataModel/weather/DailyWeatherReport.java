package de.exxcellent.challenge.dataModel.weather;

/**
 * A pure data class, representing a row in the weather.csv file
 */
public class DailyWeatherReport {

    private final int day;

    private final int maxTemperature;

    private final int minTemperature;

    public DailyWeatherReport(int day, int maxTemperature, int minTemperature){
        this.day = day;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public int getDay() {
        return day;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }
}
