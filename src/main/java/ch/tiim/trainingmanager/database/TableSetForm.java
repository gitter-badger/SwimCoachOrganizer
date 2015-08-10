package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.SetFocus;
import ch.tiim.trainingmanager.database.model.SetForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableSetForm extends Table {

    private PreparedStatement getSetFormStmt;
    private PreparedStatement addSetFormStmt;
    private PreparedStatement updateSetFormStmt;
    private PreparedStatement getAllFormsStmt;
    private PreparedStatement deleteFormStmt;

    TableSetForm(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("set_form/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getSetFormStmt = db.getStmtFile("set_form/get.sql");
        addSetFormStmt = db.getStmtFile("set_form/add.sql");
        updateSetFormStmt = db.getStmtFile("set_form/update.sql");
        getAllFormsStmt = db.getStmtFile("set_form/get_all.sql");
        deleteFormStmt = db.getStmtFile("set_form/delete.sql");
    }


    public SetFocus getSetForm(int formId) throws SQLException {
        getSetFormStmt.setInt(1, formId);
        ResultSet set = getSetFormStmt.executeQuery();
        return new SetFocus(formId, set.getString("name"), set.getString("abbr"), set.getString("notes"));
    }

    public void addSetForm(SetForm form) throws SQLException {
        addSetFormStmt.setString(1, form.getName());
        addSetFormStmt.setString(2, form.getAbbr());
        addSetFormStmt.setString(3, form.getNotes());
        addSetFormStmt.executeUpdate();
    }

    public void updateSetForm(SetForm form) throws SQLException {
        updateSetFormStmt.setString(1, form.getName());
        updateSetFormStmt.setString(2, form.getAbbr());
        updateSetFormStmt.setString(3, form.getNotes());
        updateSetFormStmt.setInt(4, form.getId());
        updateSetFormStmt.executeUpdate();
    }

    public List<SetForm> getAllForms() throws SQLException {
        List<SetForm> forms = new ArrayList<>();
        ResultSet rs = getAllFormsStmt.executeQuery();
        while (rs.next()) {
            SetForm form = new SetForm(
                    rs.getInt("form_id"),
                    rs.getString("name"),
                    rs.getString("abbr"),
                    rs.getString("notes")
            );
            forms.add(form);
        }
        return forms;
    }

    public void deleteSetForm(int id) throws SQLException{
        deleteFormStmt.setInt(1, id);
        deleteFormStmt.executeUpdate();
    }
}
