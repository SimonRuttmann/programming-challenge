package de.exxcellent.challenge.algorithms;

import de.exxcellent.challenge.dataModel.FootballTeam;
import de.exxcellent.challenge.dataModel.FootballTeamCollection;
import de.exxcellent.challenge.dataModel.MonthlyWeatherReport;

import java.util.stream.Collectors;

public class SmallestGoalSpreadCalculator {

    /**
     * Calculates the minimum of (MxT-MnT) for each DailyWeatherReport and returns the day of this weather report
     */
    public String resolveTeamWithSmallestGoalSpread(FootballTeamCollection footballTeamCollection){

        var goalsGoalsAllowedPairs = footballTeamCollection.getFootballTeams().stream().
                map(footballTeam -> new Pair<>(footballTeam.getGoals(), footballTeam.getGoalsAllowed())).
                collect(Collectors.toList());

        var resultIndex = new MinimumDifference().calcMinimumDifference(goalsGoalsAllowedPairs);

        return footballTeamCollection.getFootballTeams().get(resultIndex).getTeam();
    }
}
