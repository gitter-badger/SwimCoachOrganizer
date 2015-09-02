package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Training;

import java.util.LinkedList;
import java.util.List;

public class JDBCTraining extends Table implements ch.tiim.sco.database.TableTraining {

    public JDBCTraining(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public void addTraining(Training t) {
    }

    @Override
    public void updateTraining(Training t) {
    }

    @Override
    public void deleteTraining(Training t) {
    }

    @Override
    public List<Training> getAllTrainings() {
        return new LinkedList<>();
    }
}
