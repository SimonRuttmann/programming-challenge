package de.exxcellent.challenge.dataModel.football;

import de.exxcellent.challenge.dataModel.IDataClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A pure data class representing a collection of football teams
 * Represents the hole football.csv file
 */
public class FootballTeamCollection implements IDataClass {

    private final List<FootballTeam> footballTeams = new ArrayList<>();

    public List<FootballTeam> getFootballTeams(){
        return footballTeams;
    }

    public void addFootballTeam(FootballTeam footballTeam){
        footballTeams.add(footballTeam);
    }
}
