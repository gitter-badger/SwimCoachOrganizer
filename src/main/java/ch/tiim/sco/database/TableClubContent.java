package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.util.List;

public interface TableClubContent {
    void addTeam(Club c, Team t);

    void deleteTeam(Club c, Team t);

    List<Team> getTeams(Club c);

    List<Team> getNotTeams(Club c);
}
