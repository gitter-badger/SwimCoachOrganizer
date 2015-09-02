package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetForm;

import java.util.LinkedList;
import java.util.List;


public class TableSetForm extends Table {

    TableSetForm(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }


    public void addSetForm(SetForm form) {
    }

    public void updateSetForm(SetForm form) {
    }

    public void deleteSetForm(SetForm form) {
    }

    public List<SetForm> getAllForms() {
        return new LinkedList<>();
    }
}
