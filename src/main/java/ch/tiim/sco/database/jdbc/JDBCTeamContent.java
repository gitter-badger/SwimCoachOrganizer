package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCTeamContent extends Table implements ch.tiim.sco.database.TableTeamContent {

    public JDBCTeamContent(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public List<TeamMember> getMembers(Team t) {
        return new LinkedList<>();
    }

    @Override
    public void addMember(Team t, TeamMember m) {
    }

    @Override
    public void deleteMember(Team t, TeamMember m) {
    }

    @Override
    public List<TeamMember> getNotMembers(Team t) {
        return new LinkedList<>();
    }
}
