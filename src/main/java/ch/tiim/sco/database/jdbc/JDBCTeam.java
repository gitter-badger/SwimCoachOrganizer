package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTeam extends Table implements ch.tiim.sco.database.TableTeam {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTeam.class.getName());
    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement update;
    private NamedParameterPreparedStatement getAll;


    public JDBCTeam(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() throws SQLException {
        add = db.getPrepStmt(getSql("add"));
        delete = db.getPrepStmt(getSql("delete"));
        update = db.getPrepStmt(getSql("update"));
        getAll = db.getPrepStmt(getSql("get_all"));
    }

    @Override
    public void deleteTeam(Team t) throws SQLException {
        delete.setInt("id", t.getId());
        testUpdate(delete);
    }

    @Override
    public void addTeam(Team t) throws SQLException {
        add.setString("name", t.getName());
        testUpdate(add);
        t.setId(getGenKey(add));
    }

    @Override
    public void updateTeam(Team t) throws SQLException {
        update.setString("name", t.getName());
        update.setInt("id", t.getId());
        testUpdate(update);
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        ResultSet rs = getAll.executeQuery();
        List<Team> l = new ArrayList<>();
        while (rs.next()) {
            l.add(getTeam(rs));
        }
        return l;
    }

    static Team getTeam(ResultSet rs) throws SQLException {
        return new Team(
                rs.getInt("team_id"),
                rs.getString("name")
        );
    }
}
