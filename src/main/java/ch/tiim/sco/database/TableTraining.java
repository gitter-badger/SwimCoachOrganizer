package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Training;

import java.util.LinkedList;
import java.util.List;

public class TableTraining extends Table {

    TableTraining(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public void addTraining(Training t) {
    }

    public void updateTraining(Training t) {
    }

    public void deleteTraining(Training t) {
    }

    public List<Training> getAllTrainings() {
        return new LinkedList<>();
    }
}
