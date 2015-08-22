package ch.tiim.sco.database;

import ch.tiim.log.Log;
import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.jooq.Record;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.*;

public class TableTrainingContent extends Table {
    private static final Log LOGGER = new Log(TableTrainingContent.class);

    TableTrainingContent(DatabaseController db) throws SQLException {
        super(db);
    }

    public List<IndexedSet> getSetsForTraining(Training training) throws SQLException {
        Result<Record> fetch = db.getDsl().select()
                .from(SETS)
                .join(TRAINING_CONTENT).onKey(TRAINING_CONTENT.SET_ID)
                .join(SET_FORM).onKey(SET_FORM.FORM_ID)
                .join(SET_FOCUS).onKey(SET_FOCUS.FOCUS_ID)
                .where(TRAINING_CONTENT.TRAINING_ID.equal(training.getId()))
                .fetch();
        return fetch.into(IndexedSet.class);
    }

    public void addSetToTraining(Training t, Set set, int index) throws SQLException {
        db.getDsl().insertInto(TRAINING_CONTENT,
                TRAINING_CONTENT.TRAINING_ID, TRAINING_CONTENT.SET_ID, TRAINING_CONTENT.INDX)
                .values(t.getId(), set.getId(), index);
    }

    public void deleteSet(Training t, Set s, int index) throws SQLException {
        db.getDsl().delete(TRAINING_CONTENT)
                .where(TRAINING_CONTENT.TRAINING_ID.equal(t.getId())
                        .and(TRAINING_CONTENT.SET_ID.equal(s.getId()))
                        .and(TRAINING_CONTENT.INDX.equal(index)))
                .execute();
    }

    public void updateIndex(Training t, int index, boolean up) throws SQLException {
        throw new RuntimeException("NotImplemented");
    }
}
