package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.SetFocus;
import ch.tiim.sco.database.model.SetForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class JDBCSets extends Table implements ch.tiim.sco.database.TableSets {

    private static final Logger LOGGER = LogManager.getLogger(JDBCSets.class.getName());

    private NamedParameterPreparedStatement add;
    private NamedParameterPreparedStatement update;
    private NamedParameterPreparedStatement delete;
    private NamedParameterPreparedStatement getAll;

    public JDBCSets(DatabaseController db) throws SQLException {
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
    public void addSet(Set set) throws SQLException {
        add.setString("name", set.getName());
        add.setString("content", set.getContent());
        add.setInt("distance1", set.getDistance1());
        add.setInt("distance2", set.getDistance2());
        add.setInt("distance3", set.getDistance3());
        add.setInt("intensity", set.getIntensity());
        if (set.getFocus() == null) {
            add.setNull("focus_id", Types.INTEGER);
        } else {
            add.setInt("focus_id", set.getFocus().getId());
        }
        if (set.getForm() == null) {
            add.setNull("form_id", Types.INTEGER);
        } else {
            add.setInt("form_id", set.getForm().getId());
        }
        add.setString("notes", set.getNotes());
        add.setInt("interval", set.getInterval());
        add.setBoolean("is_pause", set.isPause());
        testUpdate(add);
        set.setId(getGenKey(add));
    }

    @Override
    public void updateSet(Set set) throws SQLException {
        update.setString("name", set.getName());
        update.setString("content", set.getContent());
        update.setInt("distance1", set.getDistance1());
        update.setInt("distance2", set.getDistance2());
        update.setInt("distance3", set.getDistance3());
        update.setInt("intensity", set.getIntensity());
        if (set.getFocus() == null) {
            update.setNull("focus_id", Types.INTEGER);
        } else {
            update.setInt("focus_id", set.getFocus().getId());
        }
        if (set.getForm() == null) {
            update.setNull("form_id", Types.INTEGER);
        } else {
            update.setInt("form_id", set.getForm().getId());
        }
        update.setString("notes", set.getNotes());
        update.setInt("interval", set.getInterval());
        update.setBoolean("is_pause", set.isPause());
        update.setInt("id", set.getId());
        testUpdate(update);
    }

    @Override
    public void deleteSet(Set set) throws SQLException {
        delete.setInt("id", set.getId());
        testUpdate(delete);
    }

    @Override
    public List<Set> getAllSets() throws SQLException {
        ResultSet rs = getAll.executeQuery();
        List<Set> l = new LinkedList<>();
        while (rs.next()) {
            l.add(getSet(rs));
        }
        return l;
    }

    static Set getSet(ResultSet rs) throws SQLException {
        SetForm form = null;
        if (rs.getInt("form_id") != 0) {
            form = JDBCSetForm.getSetForm(rs, "form_");
        }
        SetFocus focus = null;
        if (rs.getInt("focus_id") != 0) {
            focus = JDBCSetFocus.getSetFocus(rs, "focus_");
        }
        return new Set(
                rs.getInt("set_id"),
                rs.getString("name"),
                rs.getString("content"),
                rs.getInt("distance_f1"),
                rs.getInt("distance_f2"),
                rs.getInt("distance_f3"),
                rs.getInt("intensity"),
                focus,
                form,
                rs.getString("notes"),
                rs.getInt("interval"),
                rs.getBoolean("is_pause")
        );
    }
}
