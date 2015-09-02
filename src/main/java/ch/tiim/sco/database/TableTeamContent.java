package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.util.List;

public interface TableTeamContent {
    List<TeamMember> getMembersForTeam(Team t)throws Exception;

    void addMemberToTeam(Team t, TeamMember m)throws Exception;

    void removeMemberFromTeam(Team t, TeamMember m)throws Exception;

    List<TeamMember> getMembersNotInTeam(Team t)throws Exception;
}
