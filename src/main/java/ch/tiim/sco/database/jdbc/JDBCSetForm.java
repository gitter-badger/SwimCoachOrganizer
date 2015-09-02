package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.SetForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class JDBCSetForm extends Table implements ch.tiim.sco.database.TableSetForm {

    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement update;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement getAll;

    public JDBCSetForm(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() throws SQLException {
        add = db.getPrepStmt(getSql("add"));
        update = db.getPrepStmt(getSql("update"));
        delete = db.getPrepStmt(getSql("delete"));
        getAll = db.getPrepStmt(getSql("get_all"));
    }

    @Override
    public void addSetForm(SetForm form) throws SQLException {
        add.setString("name", form.getName());
        add.setString("abbr", form.getAbbr());
        add.setString("notes", form.getNotes());
        testUpdate(add);
        form.setId(getGenKey(add));
    }

    @Override
    public void updateSetForm(SetForm form) throws SQLException {
        update.setString("name", form.getName());
        update.setString("abbr", form.getAbbr());
        update.setString("notes", form.getNotes());
        update.setInt("id", form.getId());
        testUpdate(update);
    }

    @Override
    public void deleteSetForm(SetForm form) throws SQLException {
        delete.setInt("id", form.getId());
        testUpdate(delete);
    }

    @Override
    public List<SetForm> getAllForms() throws SQLException {
        ResultSet rs = getAll.executeQuery();
        List<SetForm> l = new LinkedList<>();
        while (rs.next()) {
            l.add(getSetForm(rs));
        }
        return l;
    }

    static SetForm getSetForm(ResultSet rs) throws SQLException {
        return new SetForm(
                rs.getInt("form_id"),
                rs.getString("name"),
                rs.getString("abbr"),
                rs.getString("notes")
        );
    }
}
