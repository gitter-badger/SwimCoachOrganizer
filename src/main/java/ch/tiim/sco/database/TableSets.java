package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

public interface TableSets {
    void addSet(Set set);

    void updateSet(Set set);

    void deleteSet(Set set);

    void export(Path p) throws SQLException, IOException;

    List<Set> getAllSets();
}
