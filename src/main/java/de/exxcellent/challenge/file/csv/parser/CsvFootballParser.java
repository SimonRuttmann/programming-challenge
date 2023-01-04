package de.exxcellent.challenge.file.csv.parser;

import de.exxcellent.challenge.dataModel.football.FootballTeam;
import de.exxcellent.challenge.dataModel.football.FootballTeamCollection;
import de.exxcellent.challenge.file.csv.reader.CsvContent;

import static de.exxcellent.challenge.file.csv.parser.CsvUtil.getIndexOrThrow;

/**
 * Implementation of IParser. Contains logic to create a FootballTeamCollection from a csv file
 */
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
