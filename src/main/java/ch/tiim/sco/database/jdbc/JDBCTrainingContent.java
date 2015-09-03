package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTrainingContent extends Table implements ch.tiim.sco.database.TableTrainingContent {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTrainingContent.class.getName());


    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement get;
    private NamedParameterPreparedStatement updateIndex;

    public JDBCTrainingContent(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() throws SQLException {
        add = db.getPrepStmt(getSql("add"));
        delete = db.getPrepStmt(getSql("delete"));
        get = db.getPrepStmt(getSql("get"));
        updateIndex = db.getPrepStmt(getSql("update_index"));
    }


    @Override
    public void addSet(Training t, Set set, int index) throws SQLException {
        add.setInt("training_id", t.getId());
        add.setInt("set_id", set.getId());
        add.setInt("index", index);
        LOGGER.debug(MARKER_QUERRY, add);
        testUpdate(add);
    }

    @Override
    public void deleteSet(Training t, Set s, int index) throws SQLException {
        delete.setInt("training_id", t.getId());
        delete.setInt("index", index);
        LOGGER.debug(MARKER_QUERRY, delete);
        testUpdate(delete);
    }

    @Override
    public List<IndexedSet> getSets(Training training) throws SQLException {
        get.setInt("training_id", training.getId());
        LOGGER.debug(MARKER_QUERRY, get);
        ResultSet rs = get.executeQuery();
        List<IndexedSet> l = new ArrayList<>();
        while (rs.next()) {
            l.add(getIndexedSet(rs));
        }
        return l;
    }

    @Override
    public void updateIndex(Training tr, int index, boolean up) throws SQLException {
        updateIndex.setInt("low", index + (up ? -1 : 0));
        updateIndex.setInt("high", index + (!up ? 1 : 0));
        updateIndex.setInt("training_id", tr.getId());
        LOGGER.debug(MARKER_QUERRY, updateIndex);
        testUpdate(updateIndex);
    }

    static IndexedSet getIndexedSet(ResultSet rs) throws SQLException {
        return new IndexedSet(
                rs.getInt("index"),
                JDBCSets.getSet(rs)
        );
    }
}
