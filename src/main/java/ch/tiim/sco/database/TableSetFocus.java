package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetFocus;

import java.util.LinkedList;
import java.util.List;

public class TableSetFocus extends Table {

    TableSetFocus(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public void addSetFocus(SetFocus focus) {
    }

    public void updateSetFocus(SetFocus focus) {
    }

    public void deleteSetFocus(SetFocus focus) {
    }

    public List<SetFocus> getAllFoci() {
        return new LinkedList<>();
    }
}
