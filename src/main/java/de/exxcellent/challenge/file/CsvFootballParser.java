package de.exxcellent.challenge.file;

import de.exxcellent.challenge.dataModel.DailyWeatherReport;
import de.exxcellent.challenge.dataModel.FootballTeam;
import de.exxcellent.challenge.dataModel.FootballTeamCollection;

import static de.exxcellent.challenge.file.CsvUtil.getIndexOrThrow;

public class CsvFootballParser extends AbstractCsvParser<FootballTeamCollection> {

    @Override
    protected FootballTeamCollection mapToClass(CsvContent csvContent) {
        var footballCollection = new FootballTeamCollection();

        var teamIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.FootballConstants.TEAM);
        var goalIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.FootballConstants.GOALS);
        var goalAllowedIndex = getIndexOrThrow(csvContent.getSchema(), CsvConstants.FootballConstants.GOALS_ALLOWED);

        for (var record : csvContent.getRows().entrySet()){
            var team = record.getValue().get(teamIndex);
            var goal = record.getValue().get(goalIndex);
            var goalAllowed = record.getValue().get(goalAllowedIndex);

            var footballTeam = new FootballTeam(team, Integer.parseInt(goal), Integer.parseInt(goalAllowed));
            footballCollection.addFootballTeam(footballTeam);
        }

        return footballCollection;
    }

}
