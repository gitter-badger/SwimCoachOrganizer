package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TableSets extends Table {

    private static final Logger LOGGER = LogManager.getLogger(TableSets.class.getName());

    TableSets(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {
    }

    public void addSet(Set set) {
    }

    public void updateSet(Set set) {
    }

    public void deleteSet(Set set) {
    }

    public void export(Path p) throws SQLException, IOException {
        throw new RuntimeException("NotImplemented");
    }

    public List<Set> getAllSets() {
        return new LinkedList<>();
    }
}
