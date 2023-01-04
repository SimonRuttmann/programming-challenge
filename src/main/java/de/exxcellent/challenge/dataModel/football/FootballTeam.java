package de.exxcellent.challenge.dataModel.football;

/**
 * A pure data class, representing a row in the football.csv file
 */
public class FootballTeam {

    private final String team;

    private final int goals;

    private final int goalsAllowed;

    public FootballTeam(String team, int goals, int goalsAllowed) {
        this.team = team;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }

}
