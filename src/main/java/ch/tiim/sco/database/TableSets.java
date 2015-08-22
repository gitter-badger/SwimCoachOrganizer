package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.*;

public class TableSets extends Table {

    TableSets(DatabaseController db) throws SQLException {
        super(db);
    }

    static int normalizeDistance(int dist) {
        if (dist < 1) {
            return 1;
        }
        return dist;
    }

    public void addSet(Set set) throws SQLException {
        db.getDsl().insertInto(SETS,
                SETS.SET_ID,
                SETS.NAME,
                SETS.CONTENT,
                SETS.DISTANCE_F1,
                SETS.DISTANCE_F2,
                SETS.DISTANCE_F3,
                SETS.INTENSITY,
                SETS.FOCUS_ID,
                SETS.FORM_ID,
                SETS.NOTES,
                SETS.INTERVAL,
                SETS.IS_PAUSE)
                .values(
                        set.getId(),
                        set.getName(),
                        set.getContent(),
                        set.getDistance1(),
                        set.getDistance2(),
                        set.getDistance3(),
                        set.getIntensity(),
                        set.getFocus() == null ? null : set.getFocus().getId(),
                        set.getForm() == null ? null : set.getForm().getId(),
                        set.getNotes(),
                        set.getInterval(),
                        set.isPause()
                ).execute();
    }

    public void updateSet(Set set) throws SQLException {
        db.getDsl().newRecord(SETS, set).update();
    }

    public void deleteSet(Set set) throws SQLException {
        db.getDsl().newRecord(SETS, set).delete();
    }

    public void export(Path p) throws SQLException, IOException {
        throw new RuntimeException("NotImplemented");
    }

    public List<Set> getAllSets() throws SQLException {
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
                SET_FORM.FORM_ID,
                SET_FORM.NAME.as("form_name"),
                SET_FORM.ABBR.as("form_abbr"))
                .from(SETS)
                .leftOuterJoin(SET_FOCUS).onKey()
                .leftOuterJoin(SET_FORM).onKey()
                .fetch().into(Set.class);
    }
}
