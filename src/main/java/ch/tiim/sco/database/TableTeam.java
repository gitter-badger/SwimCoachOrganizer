package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;

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
        db.getStatement().executeUpdate(db.getSql("team/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getAllTeamsStmt = db.getStmtFile("team/get_all.sql");
        deleteTeam = db.getStmtFile("team/delete.sql");
        addTeam = db.getStmtFile("team/add.sql");
        editTeam = db.getStmtFile("team/update.sql");
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        ResultSet rs = getAllTeamsStmt.executeQuery();
        while (rs.next()) {
            Team t = getTeam(rs);
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

    Team getTeam(ResultSet rs) throws SQLException {
        return new Team(rs.getInt("team_id"), rs.getString("name"));
    }
}
