package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Swimmer;

import java.sql.SQLException;
import java.util.List;

public interface TableResult {
    void addResult(Swimmer s, ch.tiim.sco.database.model.Result r) throws SQLException;

    void updateResult(ch.tiim.sco.database.model.Result r) throws SQLException;

    void deleteResult(ch.tiim.sco.database.model.Result r) throws SQLException;

    List<ch.tiim.sco.database.model.Result> getResults(Swimmer m) throws SQLException;
}
