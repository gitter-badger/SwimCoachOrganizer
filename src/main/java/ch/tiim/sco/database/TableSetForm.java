package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.records.SetFormRecord;
import ch.tiim.sco.database.model.SetForm;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.SET_FORM;


public class TableSetForm extends Table {

    TableSetForm(DatabaseController db) {
        super(db);
    }


    public void addSetForm(SetForm form) {
        SetFormRecord f = db.getDsl().newRecord(SET_FORM, form);
        f.store();
    }

    public void updateSetForm(SetForm form) {
        SetFormRecord f = db.getDsl().newRecord(SET_FORM, form);
        f.update();
    }

    public void deleteSetForm(SetForm form) {
        db.getDsl().delete(SET_FORM).where(SET_FORM.FORM_ID.equal(form.getId())).execute();
    }

    public List<SetForm> getAllForms() {
        return db.getDsl().select().from(SET_FORM).fetch().into(SetForm.class);
    }
}
