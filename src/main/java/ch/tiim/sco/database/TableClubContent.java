package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableClubContent extends Table {


    private PreparedStatement addStmt;
    private PreparedStatement getTeamsStmt;

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
        getTeamsStmt = db.getStmtFile("club_content/get_team.sql");
    }

    private void addContent(Club c, Team t) throws SQLException {
        addStmt.setInt(1, c.getId());
        addStmt.setInt(2, t.getId());
    }

    private List<Team> getTeams(Club c) throws SQLException {
        List<Team> teams = new ArrayList<>();
        getTeamsStmt.setInt(1, c.getId());
        ResultSet rs = getTeamsStmt.executeQuery();
        while (rs.next()) {
            teams.add(db.getTblTeam().getTeam(rs));
        }
        return teams;
    }
}
