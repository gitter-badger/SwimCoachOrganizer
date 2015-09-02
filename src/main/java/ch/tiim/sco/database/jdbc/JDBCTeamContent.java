package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.util.LinkedList;
import java.util.List;

public class JDBCTeamContent extends Table {

    public JDBCTeamContent(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public List<TeamMember> getMembersForTeam(Team t) {
        return new LinkedList<>();
    }

    public void addMemberToTeam(Team t, TeamMember m) {
    }

    public void removeMemberFromTeam(Team t, TeamMember m) {
    }

    public List<TeamMember> getMembersNotInTeam(Team t) {
        return new LinkedList<>();
    }
}
