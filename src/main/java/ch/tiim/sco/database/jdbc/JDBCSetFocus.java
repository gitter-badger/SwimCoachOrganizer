package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.SetFocus;

import java.util.LinkedList;
import java.util.List;

public class JDBCSetFocus extends Table implements ch.tiim.sco.database.TableSetFocus {

    public JDBCSetFocus(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public void addSetFocus(SetFocus focus) {
    }

    @Override
    public void updateSetFocus(SetFocus focus) {
    }

    @Override
    public void deleteSetFocus(SetFocus focus) {
    }

    @Override
    public List<SetFocus> getAllFoci() {
        return new LinkedList<>();
    }
}
