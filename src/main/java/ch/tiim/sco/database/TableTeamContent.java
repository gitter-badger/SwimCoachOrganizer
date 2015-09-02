package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.util.List;

public interface TableTeamContent {
    List<TeamMember> getMembersForTeam(Team t);

    void addMemberToTeam(Team t, TeamMember m);

    void removeMemberFromTeam(Team t, TeamMember m);

    List<TeamMember> getMembersNotInTeam(Team t);
}
