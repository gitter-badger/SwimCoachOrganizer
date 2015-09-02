package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCClubContent extends Table implements ch.tiim.sco.database.TableClubContent {

    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement get;
    private NamedParameterPreparedStatement getNot;

    public JDBCClubContent(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() throws SQLException {
        add = db.getPrepStmt(getSql("add"));
        delete = db.getPrepStmt(getSql("delete"));
        get = db.getPrepStmt(getSql("get"));
        getNot = db.getPrepStmt(getSql("get_not"));
    }

    @Override
    public void addTeam(Club c, Team t) throws SQLException {
        add.setInt("club_id", c.getId());
        add.setInt("team_id", t.getId());
        testUpdate(add);
    }

    @Override
    public void deleteTeam(Club c, Team t) throws SQLException {
        delete.setInt("club_id", c.getId());
        delete.setInt("team_id", t.getId());
        testUpdate(delete);
    }

    @Override
    public List<Team> getTeams(Club c) throws SQLException {
        get.setInt("club_id", c.getId());
        ResultSet rs = get.executeQuery();
        List<Team> l = new ArrayList<>();
        while (rs.next()) {
            l.add(JDBCTeam.getTeam(rs));
        }
        return l;
    }

    @Override
    public List<Team> getNotTeams(Club c) throws SQLException {
        getNot.setInt("club_id", c.getId());
        ResultSet rs = getNot.executeQuery();
        List<Team> l = new ArrayList<>();
        while (rs.next()) {
            l.add(JDBCTeam.getTeam(rs));
        }
        return l;
    }
}
