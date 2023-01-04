package de.exxcellent.challenge.dataModel.weather;

import de.exxcellent.challenge.dataModel.IDataClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A pure data class representing a collection of daily weather reports
 * Represents the hole weather.csv file
 */
public class MonthlyWeatherReport implements IDataClass {

    private final List<DailyWeatherReport> dailyWeatherReports = new ArrayList<>();

    public List<DailyWeatherReport> getDailyWeatherReports(){
        return dailyWeatherReports;
    }

    public void addDailyWeatherReport(DailyWeatherReport dailyWeatherReport){
        dailyWeatherReports.add(dailyWeatherReport);
    }

}
