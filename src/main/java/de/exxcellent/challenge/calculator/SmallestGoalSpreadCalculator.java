package de.exxcellent.challenge.calculator;

import de.exxcellent.challenge.algorithms.MinimumDifference;
import de.exxcellent.challenge.dataModel.football.FootballTeamCollection;
import de.exxcellent.challenge.toolbox.Pair;

import java.util.stream.Collectors;

public class SmallestGoalSpreadCalculator {

    /**
     * Calculates the minimum of goal - goals_allowed
     * for each football team and returns the team with the smallest difference
     */
    public String resolveTeamWithSmallestGoalSpread(FootballTeamCollection footballTeamCollection){

        var goalsGoalsAllowedPairs = footballTeamCollection.getFootballTeams().stream().
                map(footballTeam -> new Pair<>(footballTeam.getGoals(), footballTeam.getGoalsAllowed())).
                collect(Collectors.toList());

        var resultIndex = new MinimumDifference().calcMinimumDifference(goalsGoalsAllowedPairs);

        return footballTeamCollection.getFootballTeams().get(resultIndex).getTeam();
    }
}
