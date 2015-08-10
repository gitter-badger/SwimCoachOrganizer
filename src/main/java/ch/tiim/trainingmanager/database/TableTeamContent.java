package ch.tiim.trainingmanager.database;

import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.model.Team;
import ch.tiim.trainingmanager.database.model.TeamMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableTeamContent extends Table {
    private static final Log LOGGER = new Log(TableTeamContent.class);
    private PreparedStatement getMembersForTeamStmt;
    private PreparedStatement addMembersToTeamStmt;
    private PreparedStatement deleteMembersFromTeamStmt;
    private PreparedStatement getMembersNotInTeamStmt;

    protected TableTeamContent(DatabaseController db) {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("team_content/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getMembersForTeamStmt = db.getStmtFile("team_content/get_members.sql");
        addMembersToTeamStmt = db.getStmtFile("team_content/add.sql");
        deleteMembersFromTeamStmt = db.getStmtFile("team_content/delete.sql");
        getMembersNotInTeamStmt = db.getStmtFile("team_content/get_not_members.sql");
    }

    public List<TeamMember> getMembersForTeam(Team t) throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        getMembersForTeamStmt.setInt(1, t.getId());
        ResultSet rs = getMembersForTeamStmt.executeQuery();
        while (rs.next()) {
            members.add(TableTeamMember.getMember(rs));
        }
        return members;
    }

    public void addMemberToTeam(Team t, TeamMember m) throws SQLException {
        addMembersToTeamStmt.setInt(1,m.getId());
        addMembersToTeamStmt.setInt(2, t.getId());
        addMembersToTeamStmt.executeUpdate();
    }

    public void removeMemberFromTeam(Team t, TeamMember m) throws SQLException {
        deleteMembersFromTeamStmt.setInt(1,m.getId());
        deleteMembersFromTeamStmt.setInt(2, t.getId());
        deleteMembersFromTeamStmt.executeUpdate();
    }

    public List<TeamMember> getMembersNotInTeam(Team t) throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        getMembersNotInTeamStmt.setInt(1, t.getId());
        ResultSet rs = getMembersNotInTeamStmt.executeQuery();
        while (rs.next()) {
            members.add(TableTeamMember.getMember(rs));
        }
        return members;
    }
}
