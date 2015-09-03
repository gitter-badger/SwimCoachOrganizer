package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JDBCTeamContent extends Table implements ch.tiim.sco.database.TableTeamContent {

    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement get;
    private NamedParameterPreparedStatement getNot;

    public JDBCTeamContent(DatabaseController db) throws SQLException {
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
    public void addMember(Team t, TeamMember m) throws SQLException {
        add.setInt("team_id", t.getId());
        add.setInt("member_id", m.getId());
        testUpdate(add);
    }

    @Override
    public void deleteMember(Team t, TeamMember m) throws SQLException {
        delete.setInt("team_id", t.getId());
        delete.setInt("member_id", m.getId());
        testUpdate(delete);
    }

    @Override
    public List<TeamMember> getMembers(Team t) throws SQLException {
        get.setInt("team_id", t.getId());
        ResultSet rs = get.executeQuery();
        List<TeamMember> l = new ArrayList<>();
        while (rs.next()) {
            l.add(JDBCTeamMember.getMember(rs));
        }
        return l;
    }

    @Override
    public List<TeamMember> getNotMembers(Team t) throws SQLException {
        getNot.setInt("team_id", t.getId());
        ResultSet rs = getNot.executeQuery();
        List<TeamMember> l = new ArrayList<>();
        while (rs.next()) {
            l.add(JDBCTeamMember.getMember(rs));
        }
        return l;
    }
}
