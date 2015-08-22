package ch.tiim.sco.database;

import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record;
import org.jooq.Result;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.*;

public class TableTrainingContent extends Table {
    private static final Logger LOGGER = LogManager.getLogger(TableTrainingContent.class.getName());

    TableTrainingContent(DatabaseController db) {
        super(db);
    }

    public List<IndexedSet> getSetsForTraining(Training training) {
        Result<Record> fetch = db.getDsl().select()
                .from(SETS)
                .join(TRAINING_CONTENT).onKey(TRAINING_CONTENT.SET_ID)
                .join(SET_FORM).onKey()
                .join(SET_FOCUS).onKey()
                .where(TRAINING_CONTENT.TRAINING_ID.equal(training.getId()))
                .fetch();
        return fetch.into(IndexedSet.class);
    }

    public void addSetToTraining(Training t, Set set, int index) {
        db.getDsl().insertInto(TRAINING_CONTENT,
                TRAINING_CONTENT.TRAINING_ID, TRAINING_CONTENT.SET_ID, TRAINING_CONTENT.INDX)
                .values(t.getId(), set.getId(), index);
    }

    public void deleteSet(Training t, Set s, int index) {
        db.getDsl().delete(TRAINING_CONTENT)
                .where(TRAINING_CONTENT.TRAINING_ID.equal(t.getId())
                        .and(TRAINING_CONTENT.SET_ID.equal(s.getId()))
                        .and(TRAINING_CONTENT.INDX.equal(index)))
                .execute();
    }

    public void updateIndex(Training t, int index, boolean up) {
        throw new RuntimeException("NotImplemented");
    }
}
