package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableTeam extends Table {

    private PreparedStatement getAllTeamsStmt;
    private PreparedStatement deleteTeam;
    private PreparedStatement addTeam;
    private PreparedStatement editTeam;

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
        deleteTeam = db.getStmtFile("TEAM_delete.sql");
        addTeam = db.getStmtFile("TEAM_add.sql");
        editTeam = db.getStmtFile("TEAM_update.sql");
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

    public void deleteTeam(Team t) throws SQLException {
        deleteTeam.setInt(1, t.getId());
        deleteTeam.executeUpdate();
    }

    public void addTeam(Team t) throws SQLException {
        addTeam.setString(1, t.getName());
        addTeam.executeUpdate();
    }

    public void editTeam(Team t) throws SQLException {
        editTeam.setString(1, t.getName());
        editTeam.setInt(2, t.getId());
        editTeam.executeUpdate();
    }
}
