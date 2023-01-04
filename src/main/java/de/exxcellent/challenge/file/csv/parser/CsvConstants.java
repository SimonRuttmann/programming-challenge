package de.exxcellent.challenge.file.csv.parser;

/**
 * Class holding all csv schema constants, required by the parsers
 * @see de.exxcellent.challenge.file.IParser
 */
public class CsvConstants {

    public static class WeatherConstants{

        public static final String MAX_TEMPERATURE = "MxT";
        public static final String MIN_TEMPERATURE = "MnT";
        public static final String DAY = "Day";

    }

    public static class FootballConstants{

        public static final String TEAM = "Team";
        public static final String GOALS_ALLOWED = "Goals Allowed";
        public static final String GOALS = "Goals";

    }

}
