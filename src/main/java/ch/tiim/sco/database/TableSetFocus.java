package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.records.SetFocusRecord;
import ch.tiim.sco.database.model.SetFocus;

import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.SET_FOCUS;

public class TableSetFocus extends Table {

    TableSetFocus(DatabaseController db) throws SQLException {
        super(db);
    }

    public void addSetFocus(SetFocus focus) throws SQLException {
        SetFocusRecord f = db.getDsl().newRecord(SET_FOCUS, focus);
        f.store();
    }

    public void updateSetFocus(SetFocus focus) throws SQLException {
        SetFocusRecord f = db.getDsl().newRecord(SET_FOCUS, focus);
        f.update();
    }

    public void deleteSetFocus(SetFocus focus) throws SQLException {
        db.getDsl().delete(SET_FOCUS).where(SET_FOCUS.FOCUS_ID.equal(focus.getId())).execute();
    }

    public List<SetFocus> getAllFoci() throws SQLException {
        return db.getDsl().select().from(SET_FOCUS).fetch().into(SetFocus.class);
    }
}
