package ch.tiim.trainingmanager.database;

import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.model.Set;
import ch.tiim.trainingmanager.database.model.Training;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableTraining extends Table {
    private static final Log LOGGER = new Log(TableTraining.class);
    private PreparedStatement getTrainingStmt;
    private PreparedStatement addTrainingStmt;
    private PreparedStatement updateTrainingStmt;
    private PreparedStatement getAllTrainingStmt;
    private PreparedStatement deleteTrainingStmt;

    TableTraining(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TRAINING_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getTrainingStmt = db.getStmtFile("TRAINING_get.sql");
        addTrainingStmt = db.getStmtFile("TRAINING_add.sql");
        updateTrainingStmt = db.getStmtFile("TRAINING_update.sql");
        getAllTrainingStmt = db.getStmtFile("TRAINING_get_all.sql");
        deleteTrainingStmt = db.getStmtFile("TRAINING_delete.sql");
    }


    public Training getTraining(int trainingId) throws SQLException {
        getTrainingStmt.setInt(1, trainingId);
        ResultSet set = getTrainingStmt.executeQuery();
        return new Training(trainingId, set.getString("name"));
    }

    public void addTraining(Training form) throws SQLException {
        addTrainingStmt.setString(1, form.getName());
        addTrainingStmt.executeUpdate();
    }

    public void updateTraining(int fromId, Training form) throws SQLException {
        updateTrainingStmt.setString(1, form.getName());
        updateTrainingStmt.setInt(2, fromId);
        updateTrainingStmt.executeUpdate();
    }

    public List<Training> getAllTrainings() throws SQLException {
        List<Training> list = new ArrayList<>();
        ResultSet set = getAllTrainingStmt.executeQuery();
        while (set.next()) {
            int id = set.getInt("training_id");
            list.add(new Training(id, set.getString("name")));
        }
        return list;
    }

    public void deleteTraining(Training t) throws SQLException {
        deleteTrainingStmt.setInt(1, t.getId());
        deleteTrainingStmt.executeUpdate();
    }
}
