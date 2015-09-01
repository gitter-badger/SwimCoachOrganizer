package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.TrainingContent;
import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Record19;
import org.jooq.Result;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.*;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.sum;

public class TableTrainingContent extends Table {
    private static final Logger LOGGER = LogManager.getLogger(TableTrainingContent.class.getName());

    TableTrainingContent(DatabaseController db) {
        super(db);
    }

    public List<IndexedSet> getSetsForTraining(Training training) {
        Result<Record19<Integer, String, String, String, Integer, String, String, String, Integer, String, String,
                Integer, Integer, Integer, Integer, String, Integer, Boolean, Integer>> fetcb =
                db.getDsl().select(
                        SET_FOCUS.FOCUS_ID,
                        SET_FOCUS.NAME.as("focus_name"),
                        SET_FOCUS.ABBR.as("focus_abbr"),
                        SET_FOCUS.NOTES.as("focus_notes"),
                        SET_FORM.FORM_ID,
                        SET_FORM.NAME.as("form_name"),
                        SET_FORM.ABBR.as("form_abbr"),
                        SET_FORM.NOTES.as("form_notes"),
                        SETS.SET_ID,
                        SETS.NAME,
                        SETS.CONTENT,
                        SETS.DISTANCE_F1,
                        SETS.DISTANCE_F2,
                        SETS.DISTANCE_F3,
                        SETS.INTENSITY,
                        SETS.NOTES,
                        SETS.INTERVAL,
                        SETS.IS_PAUSE,
                        TRAINING_CONTENT.INDX
                )
                        .from(SETS)
                        .join(TRAINING_CONTENT).onKey(TRAINING_CONTENT.SET_ID)
                        .leftOuterJoin(SET_FORM).onKey()
                        .leftOuterJoin(SET_FOCUS).onKey()
                        .where(TRAINING_CONTENT.TRAINING_ID.equal(training.getId()))
                        .fetch();
        return fetcb.into(IndexedSet.class);
    }

    public void addSetToTraining(Training t, Set set, int index) {
        db.getDsl().insertInto(TRAINING_CONTENT,
                TRAINING_CONTENT.TRAINING_ID, TRAINING_CONTENT.SET_ID, TRAINING_CONTENT.INDX)
                .values(t.getId(), set.getId(), index)
                .execute();
    }

    public void deleteSet(Training t, Set s, int index) {
        db.getDsl().delete(TRAINING_CONTENT)
                .where(TRAINING_CONTENT.TRAINING_ID.equal(t.getId())
                        .and(TRAINING_CONTENT.SET_ID.equal(s.getId()))
                        .and(TRAINING_CONTENT.INDX.equal(index)))
                .execute();
    }

    public void updateIndex(Training tr, int index, boolean up) {
        TrainingContent t = TRAINING_CONTENT.as("t");
        LOGGER.debug("SQL:\n" + db.getDsl().update(TRAINING_CONTENT)
                .set(TRAINING_CONTENT.INDX,
                        select(sum(t.INDX).coerce(Integer.class))
                                .from(t)
                                .where(t.INDX.equal(index)
                                        .or(t.INDX.equal(index + (up ? -1 : +1))))
                                .asField().minus(TRAINING_CONTENT.INDX).coerce(Integer.class)
                ).where(TRAINING_CONTENT.INDX.equal(index)
                        .or(TRAINING_CONTENT.INDX.equal(index + (up ? -1 : +1))))
                .execute());
    }
}
