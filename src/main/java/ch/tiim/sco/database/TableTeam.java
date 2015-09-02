package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;

import java.util.List;

public interface TableTeam {
    void deleteTeam(Team t);

    void addTeam(Team t);

    void editTeam(Team t);

    List<Team> getAllTeams();
}
