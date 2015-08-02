package ch.tiim.trainingmanager.database;

import ch.tiim.log.Log;
import ch.tiim.trainingmanager.database.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableTrainingContent extends Table {
    private static final Log LOGGER = new Log(TableTrainingContent.class);

    private PreparedStatement getSetsForTableStmt;
    private PreparedStatement addSetToTrainingStmt;
    private PreparedStatement deleteSetFromTrainingStmt;
    private PreparedStatement updateIndexStmt;

    TableTrainingContent(DatabaseController db, boolean isNew) throws SQLException {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TRAINING-CONTENT_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getSetsForTableStmt = db.getStatement(db.getSql("TRAINING-CONTENT_get_sets.sql"));
        addSetToTrainingStmt = db.getStatement(db.getSql("TRAINING-CONTENT_add_set.sql"));
        deleteSetFromTrainingStmt = db.getStatement(db.getSql("TRAINING-CONTENT_delete_set.sql"));
        updateIndexStmt = db.getStatement(db.getSql("TRAINING-CONTENT_update_index.sql"));
    }

    public List<IndexedSet> getSetsForTraining(int trainingId) throws SQLException {
        getSetsForTableStmt.setInt(1, trainingId);
        ResultSet rs = getSetsForTableStmt.executeQuery();
        ArrayList<IndexedSet> sets = new ArrayList<>();
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
                    TableSets.normalizeDistance(rs.getInt("distance_f1")),
                    TableSets.normalizeDistance(rs.getInt("distance_f2")),
                    TableSets.normalizeDistance(rs.getInt("distance_f3")), rs.getInt("intensity"), focus,
                    form, rs.getString("notes"), rs.getInt("interval"), rs.getBoolean("is_pause")
            );
            int index = rs.getInt("indx");
            sets.add(new IndexedSet(index, s));
        }
        return sets;
    }

    public void addSetToTraining(Training t, Set set, int index) throws SQLException {
        addSetToTrainingStmt.setInt(1, t.getId());
        addSetToTrainingStmt.setInt(2, set.getId());
        addSetToTrainingStmt.setInt(3, index);
        addSetToTrainingStmt.executeUpdate();
    }

    public void deleteSet(Training t, Set s, int index) throws SQLException {
        deleteSetFromTrainingStmt.setInt(1, s.getId());
        deleteSetFromTrainingStmt.setInt(2, t.getId());
        deleteSetFromTrainingStmt.setInt(3, index);
        deleteSetFromTrainingStmt.executeUpdate();
    }

    public void updateIndex(Training t, int index, boolean up) throws SQLException {
        updateIndexStmt.setInt(1, index);
        updateIndexStmt.setInt(2, index + (up ? -1 : 1));
        updateIndexStmt.setInt(3, index);
        updateIndexStmt.setInt(4, index + (up ? -1 : 1));
        updateIndexStmt.executeUpdate();
    }
}
