package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.SetFocus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableSetFocus extends Table {

    private PreparedStatement getSetFocusStmt;
    private PreparedStatement addSetFocusStmt;
    private PreparedStatement updateSetFocusStmt;
    private PreparedStatement getAllFociStmt;
    private PreparedStatement deleteFocusStmt;

    TableSetFocus(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("set_focus/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getSetFocusStmt = db.getStmtFile("set_focus/get.sql");
        addSetFocusStmt = db.getStmtFile("set_focus/add.sql");
        updateSetFocusStmt = db.getStmtFile("set_focus/update.sql");
        getAllFociStmt = db.getStmtFile("set_focus/get_all.sql");
        deleteFocusStmt = db.getStmtFile("set_focus/delete.sql");
    }

    public SetFocus getSetFocus(int focusId) throws SQLException {
        getSetFocusStmt.setInt(1, focusId);
        ResultSet set = getSetFocusStmt.executeQuery();
        return new SetFocus(focusId, set.getString("name"), set.getString("abbr"), set.getString("notes"));
    }

    public void addSetFocus(SetFocus focus) throws SQLException {
        addSetFocusStmt.setString(1, focus.getName());
        addSetFocusStmt.setString(2, focus.getAbbr());
        addSetFocusStmt.setString(3, focus.getNotes());
        addSetFocusStmt.executeUpdate();
    }

    public void updateSetFocus(SetFocus focus) throws SQLException {
        updateSetFocusStmt.setString(1, focus.getName());
        updateSetFocusStmt.setString(2, focus.getAbbr());
        updateSetFocusStmt.setString(3, focus.getNotes());
        updateSetFocusStmt.setInt(4, focus.getId());
        updateSetFocusStmt.executeUpdate();
    }

    public List<SetFocus> getAllFoci() throws SQLException {
        List<SetFocus> foci = new ArrayList<>();
        ResultSet rs = getAllFociStmt.executeQuery();
        while (rs.next()) {
            SetFocus focus = new SetFocus(
                    rs.getInt("focus_id"),
                    rs.getString("name"),
                    rs.getString("abbr"),
                    rs.getString("notes")
            );
            foci.add(focus);
        }
        return foci;
    }

    public void deleteSetFocus(int id) throws SQLException {
        deleteFocusStmt.setInt(1,id);
        deleteFocusStmt.executeUpdate();
    }
}
