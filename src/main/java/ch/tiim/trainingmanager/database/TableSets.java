package ch.tiim.trainingmanager.database;

import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.model.Set;
import ch.tiim.trainingmanager.database.model.SetFocus;
import ch.tiim.trainingmanager.database.model.SetForm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TableSets extends Table {
    private static final Log LOGGER = new Log(TableSets.class);

    private PreparedStatement getAllSets;
    private PreparedStatement addSet;
    private PreparedStatement deleteSet;
    private PreparedStatement updateSet;


    TableSets(DatabaseController db) throws SQLException {
        super(db);
    }

    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("sets/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getAllSets = db.getStmtFile("sets/get_all.sql");
        addSet = db.getStmtFile("sets/add.sql");
        deleteSet = db.getStmtFile("sets/delete.sql");
        updateSet = db.getStmtFile("sets/update.sql");
    }

    public List<Set> getAllSets() throws SQLException {
        ResultSet rs = getAllSets.executeQuery();
        ArrayList<Set> sets = new ArrayList<>();
        while (rs.next()) {
            SetForm form = null;
            int formId = rs.getInt("form_id");
            if (formId != 0) {
                form = new SetForm(formId, rs.getString("form_name"),
                        rs.getString("form_abbr"), rs.getString("form_notes"));
            }
            SetFocus focus = null;
            int focusId = rs.getInt("focus_id");
            if (focusId != 0) {
                focus = new SetFocus(focusId, rs.getString("focus_name"),
                        rs.getString("focus_abbr"), rs.getString("focus_notes"));
            }
            Set s = new Set(
                    rs.getInt("set_id"), rs.getString("name"), rs.getString("content"),
                    normalizeDistance(rs.getInt("distance_f1")), normalizeDistance(rs.getInt("distance_f2")),
                    normalizeDistance(rs.getInt("distance_f3")), rs.getInt("intensity"), focus,
                    form, rs.getString("notes"), rs.getInt("interval"), rs.getBoolean("is_pause")
            );
            sets.add(s);
        }
        return sets;
    }

    static int normalizeDistance(int dist) {
        if (dist < 1) {
            return 1;
        }
        return dist;
    }

    public void addSet(Set set) throws SQLException {
        addSet.setString(1, set.getName());
        addSet.setString(2, set.getContent());
        addSet.setInt(3, set.getDistance1());
        addSet.setInt(4, set.getDistance2());
        addSet.setInt(5, set.getDistance3());
        addSet.setInt(6, set.getIntensity());
        if (set.getFocus() == null || set.getFocus().getId() == 0) {
            addSet.setNull(7, Types.INTEGER);
        } else {
            addSet.setInt(7, set.getFocus().getId());
        }
        if (set.getForm() == null || set.getForm().getId() == 0) {
            addSet.setNull(8, Types.INTEGER);
        } else {
            addSet.setInt(8, set.getForm().getId());
        }
        addSet.setString(9, set.getNotes());
        addSet.setInt(10, set.getInterval());
        addSet.setBoolean(11, set.isPause());
        addSet.executeUpdate();
    }

    public void updateSet(Set set) throws SQLException {
        updateSet.setString(1, set.getName());
        updateSet.setString(2, set.getContent());
        updateSet.setInt(3, set.getDistance1());
        updateSet.setInt(4, set.getDistance2());
        updateSet.setInt(5, set.getDistance3());
        updateSet.setInt(6, set.getIntensity());
        if (set.getFocus() == null || set.getFocus().getId() == 0) {
            updateSet.setNull(7, Types.INTEGER);
        } else {
            updateSet.setInt(7, set.getFocus().getId());
        }
        if (set.getFocus() == null || set.getForm().getId() == 0) {
            updateSet.setNull(8, Types.INTEGER);
        } else {
            updateSet.setInt(8, set.getForm().getId());
        }
        updateSet.setString(9, set.getNotes());
        updateSet.setInt(10, set.getInterval());
        updateSet.setBoolean(11, set.isPause());
        updateSet.setInt(12, set.getId());
        updateSet.executeUpdate();
    }

    public void deleteSet(Set set) throws SQLException {
        deleteSet.setInt(1, set.getId());
        deleteSet.executeUpdate();
    }

    public void export(Path p) throws SQLException, IOException {
        Files.deleteIfExists(p);
        try {
            db.attach(p);
            db.getStatement().executeUpdate(db.getSql("sets/export.sql"));
            db.getStatement().executeUpdate(db.getSql("set_focus/export.sql"));
            db.getStatement().executeUpdate(db.getSql("set_form/export.sql"));
        } finally {
            db.detach();
        }
    }
}
