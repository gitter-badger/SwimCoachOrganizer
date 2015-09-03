package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.SetForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class JDBCSetForm extends Table implements ch.tiim.sco.database.TableSetForm {
    private static final Logger LOGGER = LogManager.getLogger(JDBCSetForm.class.getName());
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
        LOGGER.debug(MARKER_QUERRY, add);
        testUpdate(add);
        form.setId(getGenKey(add));
    }

    @Override
    public void updateSetForm(SetForm form) throws SQLException {
        update.setString("name", form.getName());
        update.setString("abbr", form.getAbbr());
        update.setString("notes", form.getNotes());
        update.setInt("id", form.getId());
        LOGGER.debug(MARKER_QUERRY, update);
        testUpdate(update);
    }

    @Override
    public void deleteSetForm(SetForm form) throws SQLException {
        delete.setInt("id", form.getId());
        LOGGER.debug(MARKER_QUERRY, delete);
        testUpdate(delete);
    }

    @Override
    public List<SetForm> getAllForms() throws SQLException {
        ResultSet rs = getAll.executeQuery();
        LOGGER.debug(MARKER_QUERRY, getAll);
        List<SetForm> l = new LinkedList<>();
        while (rs.next()) {
            l.add(getSetForm(rs));
        }
        return l;
    }

    static SetForm getSetForm(ResultSet rs) throws SQLException {
        return getSetForm(rs, "");
    }

    static SetForm getSetForm(ResultSet rs, String prefix) throws SQLException {
        return new SetForm(
                rs.getInt("form_id"),
                rs.getString(prefix + "name"),
                rs.getString(prefix + "abbr"),
                rs.getString(prefix + "notes")
        );
    }
}
