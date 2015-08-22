package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Training;

import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TRAINING;

public class TableTraining extends Table {

    TableTraining(DatabaseController db) throws SQLException {
        super(db);
    }

    public void addTraining(Training t) throws SQLException {
        db.getDsl().newRecord(TRAINING, t).insert();
    }

    public void updateTraining(Training t) throws SQLException {
        db.getDsl().newRecord(TRAINING, t).update();

    }

    public void deleteTraining(Training t) throws SQLException {
        db.getDsl().newRecord(TRAINING, t).delete();
    }

    public List<Training> getAllTrainings() throws SQLException {
        return db.getDsl().select().from(TRAINING).fetch().into(Training.class);
    }
}
