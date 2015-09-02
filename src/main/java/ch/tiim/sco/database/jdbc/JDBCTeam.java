package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCTeam extends Table implements ch.tiim.sco.database.TableTeam {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTeam.class.getName());

    public JDBCTeam(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public void deleteTeam(Team t) {
    }

    @Override
    public void addTeam(Team t) {
    }

    @Override
    public void editTeam(Team t) {
    }

    @Override
    public List<Team> getAllTeams() {
        return new LinkedList<>();
    }
}
