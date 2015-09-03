package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTraining extends Table implements ch.tiim.sco.database.TableTraining {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTraining.class.getName());


    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement update;
    private NamedParameterPreparedStatement getAll;

    public JDBCTraining(DatabaseController db) throws SQLException {
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
    public void addTraining(Training t) throws SQLException {
        add.setString("name", t.getName());
        LOGGER.debug(MARKER_QUERRY, add);
        testUpdate(add);
        t.setId(getGenKey(add));
    }

    @Override
    public void updateTraining(Training t) throws SQLException {
        update.setString("name", t.getName());
        update.setInt("id", t.getId());
        LOGGER.debug(MARKER_QUERRY, update);
        testUpdate(update);
    }

    @Override
    public void deleteTraining(Training t) throws SQLException {
        delete.setInt("id", t.getId());
        LOGGER.debug(MARKER_QUERRY, delete);
        testUpdate(delete);
    }

    @Override
    public List<Training> getAllTrainings() throws SQLException {
        ResultSet rs = getAll.executeQuery();
        LOGGER.debug(MARKER_QUERRY, getAll);
        List<Training> l = new ArrayList<>();
        while (rs.next()) {
            l.add(getTraining(rs));
        }
        return l;
    }

    static Training getTraining(ResultSet rs) throws SQLException {
        return new Training(
                rs.getInt("training_id"),
                rs.getString("name")
        );
    }
}
