package de.exxcellent.challenge.dataModel;

import java.util.ArrayList;
import java.util.List;

public class FootballTeamCollection {

    private final List<FootballTeam> footballTeams = new ArrayList<>();

    public List<FootballTeam> getFootballTeams(){
        return footballTeams;
    }

    public void addFootballTeam(FootballTeam footballTeam){
        footballTeams.add(footballTeam);
    }
}
