package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class JDBCTeam extends Table {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTeam.class.getName());

    public JDBCTeam(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public void deleteTeam(Team t) {
    }

    public void addTeam(Team t) {
    }

    public void editTeam(Team t) {
    }

    public List<Team> getAllTeams() {
        return new LinkedList<>();
    }
}
