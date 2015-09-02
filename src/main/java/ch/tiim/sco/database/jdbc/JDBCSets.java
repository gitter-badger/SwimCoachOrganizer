package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCSets extends Table implements ch.tiim.sco.database.TableSets {

    private static final Logger LOGGER = LogManager.getLogger(JDBCSets.class.getName());

    public JDBCSets(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {
    }

    @Override
    public void addSet(Set set) {
    }

    @Override
    public void updateSet(Set set) {
    }

    @Override
    public void deleteSet(Set set) {
    }

    @Override
    public List<Set> getAllSets() {
        return new LinkedList<>();
    }
}
