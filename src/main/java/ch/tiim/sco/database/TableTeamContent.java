package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.util.List;

public interface TableTeamContent {
    List<TeamMember> getMembers(Team t)throws Exception;

    void addMember(Team t, TeamMember m)throws Exception;

    void deleteMember(Team t, TeamMember m)throws Exception;

    List<TeamMember> getNotMembers(Team t)throws Exception;
}
