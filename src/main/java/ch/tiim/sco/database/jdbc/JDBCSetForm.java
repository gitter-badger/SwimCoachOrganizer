package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.SetForm;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class JDBCSetForm extends Table implements ch.tiim.sco.database.TableSetForm {

    public JDBCSetForm(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }


    @Override
    public void addSetForm(SetForm form) {
    }

    @Override
    public void updateSetForm(SetForm form) {
    }

    @Override
    public void deleteSetForm(SetForm form) {
    }

    @Override
    public List<SetForm> getAllForms() {
        return new LinkedList<>();
    }
}
