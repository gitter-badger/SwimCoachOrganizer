package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.records.SetsRecord;
import ch.tiim.sco.database.model.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.*;

public class TableSets extends Table {

    private static final Logger LOGGER = LogManager.getLogger(TableSets.class.getName());

    TableSets(DatabaseController db) {
        super(db);
    }

    public void addSet(Set set) {
        SetsRecord r = db.getDsl().newRecord(SETS, set);
        r.store();
        set.setId(r.getSetId());
    }

    public void updateSet(Set set) {
        db.getDsl().newRecord(SETS, set).update();
    }

    public void deleteSet(Set set) {
        db.getDsl().newRecord(SETS, set).delete();
    }

    public void export(Path p) throws SQLException, IOException {
        throw new RuntimeException("NotImplemented");
    }

    public List<Set> getAllSets() {
        return db.getDsl().select(
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
                SET_FOCUS.FOCUS_ID,
                SET_FOCUS.NAME.as("focus_name"),
                SET_FOCUS.ABBR.as("focus_abbr"),
                SET_FOCUS.NOTES.as("focus_notes"),
                SET_FORM.FORM_ID,
                SET_FORM.NAME.as("form_name"),
                SET_FORM.ABBR.as("form_abbr"),
                SET_FORM.NOTES.as("form_notes"))
                .from(SETS)
                .leftOuterJoin(SET_FOCUS).onKey()
                .leftOuterJoin(SET_FORM).onKey()
                .fetch().into(Set.class);
    }
}
