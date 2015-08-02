package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableTeam extends Table {

    private PreparedStatement getAllTeamsStmt;

    TableTeam(DatabaseController db) {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TEAM_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getAllTeamsStmt = db.getStmtFile("TEAM_get_all.sql");
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        ResultSet rs = getAllTeamsStmt.executeQuery();
        while (rs.next()) {
            Team t = new Team(rs.getInt("team_id"), rs.getString("name"));
            teams.add(t);
        }
        return teams;
    }
}
