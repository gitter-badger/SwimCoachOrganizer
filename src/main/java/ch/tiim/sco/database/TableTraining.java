package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.records.TrainingRecord;
import ch.tiim.sco.database.model.Training;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TRAINING;

public class TableTraining extends Table {

    TableTraining(DatabaseController db) {
        super(db);
    }

    public void addTraining(Training t) {
        TrainingRecord r = db.getDsl().newRecord(TRAINING, t);
        r.insert();
        t.setId(r.getTrainingId());
    }

    public void updateTraining(Training t) {
        db.getDsl().newRecord(TRAINING, t).update();

    }

    public void deleteTraining(Training t) {
        db.getDsl().newRecord(TRAINING, t).delete();
    }

    public List<Training> getAllTrainings() {
        return db.getDsl().select().from(TRAINING).fetch().into(Training.class);
    }
}
