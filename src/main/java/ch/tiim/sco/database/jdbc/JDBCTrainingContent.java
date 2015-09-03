package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCTrainingContent extends Table implements ch.tiim.sco.database.TableTrainingContent {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTrainingContent.class.getName());

    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement get;
    private NamedParameterPreparedStatement getNot;

    public JDBCTrainingContent(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public List<IndexedSet> getSets(Training training) {
        return new LinkedList<>();
    }

    @Override
    public void addSet(Training t, Set set, int index) {
    }

    @Override
    public void deleteSet(Training t, Set s, int index) {
    }

    @Override
    public void updateIndex(Training tr, int index, boolean up) {
    }
}
