package ch.tiim.sco.database;

import ch.tiim.log.Log;
import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableClubContent extends Table {

    private static final Log LOGGER = new Log(TableClubContent.class);
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getTeamsStmt;
    private PreparedStatement getNotTeamsStmt;

    protected TableClubContent(DatabaseController db) {
        super(db);
    }

    @Override
    void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("club_content/make.sql"));
    }

    @Override
    void loadStatements() throws SQLException {
        addStmt = db.getStmtFile("club_content/add.sql");
        deleteStmt = db.getStmtFile("club_content/delete.sql");
        getTeamsStmt = db.getStmtFile("club_content/get_content.sql");
        getNotTeamsStmt = db.getStmtFile("club_content/get_not_content.sql");
    }

    public void addTeam(Club c, Team t) throws SQLException {
        addStmt.setInt(1, c.getId());
        addStmt.setInt(2, t.getId());
        addStmt.executeUpdate();
    }

    public void deleteTeam(Club c, Team t) throws SQLException {
        deleteStmt.setInt(1, c.getId());
        deleteStmt.setInt(2, t.getId());
        deleteStmt.executeUpdate();
    }

    public List<Team> getTeams(Club c) throws SQLException {
        List<Team> teams = new ArrayList<>();
        getTeamsStmt.setInt(1, c.getId());
        ResultSet rs = getTeamsStmt.executeQuery();
        while (rs.next()) {
            teams.add(db.getTblTeam().getTeam(rs));
        }
        return teams;
    }

    public List<Team> getNotTeams(Club c) throws SQLException {
        List<Team> teams = new ArrayList<>();
        getNotTeamsStmt.setInt(1, c.getId());
        ResultSet rs = getNotTeamsStmt.executeQuery();
        while (rs.next()) {
            teams.add(db.getTblTeam().getTeam(rs));
        }
        return teams;
    }
}
