package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;

import java.util.List;

public interface TableTeam {
    void deleteTeam(Team t)throws Exception;

    void addTeam(Team t)throws Exception;

    void editTeam(Team t)throws Exception;

    List<Team> getAllTeams()throws Exception;
}
