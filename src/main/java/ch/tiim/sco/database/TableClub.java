package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableClub extends Table {

    private PreparedStatement addStmt;
    private PreparedStatement getAllStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement updateStmt;

    public TableClub(DatabaseController db) {
        super(db);
    }

    @Override
    void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("club/make.sql"));
    }

    @Override
    void loadStatements() throws SQLException {
        addStmt = db.getStmtFile("club/add.sql");
        getAllStmt = db.getStmtFile("club/get_all.sql");
        deleteStmt = db.getStmtFile("club/delete.sql");
        updateStmt = db.getStmtFile("club/update.sql");
    }

    public void addClub(Club c) throws SQLException {
        addStmt.setString(1, c.getName());
        addStmt.setString(2, c.getNameShort());
        addStmt.setString(3, c.getNameEnglish());
        addStmt.setString(4, c.getNameEnglishShort());
        addStmt.setString(5, c.getCode());
        addStmt.setString(6, c.getNationality());
        addStmt.setInt(7, c.getIdExtern());
        addStmt.executeUpdate();
    }

    public List<Club> getAll() throws SQLException {
        List<Club> clubs = new ArrayList<>();
        ResultSet rs = getAllStmt.executeQuery();
        while (rs.next()) {
            clubs.add(getClub(rs));
        }
        return clubs;
    }

    public void deleteClub(Club c) throws SQLException {
        deleteStmt.setInt(1, c.getId());
        deleteStmt.executeUpdate();
    }

    public void updateClub(Club c) throws SQLException {
        updateStmt.setString(1, c.getName());
        updateStmt.setString(2, c.getNameShort());
        updateStmt.setString(3, c.getNameEnglish());
        updateStmt.setString(4, c.getNameEnglishShort());
        updateStmt.setString(5, c.getCode());
        updateStmt.setString(6, c.getNationality());
        updateStmt.setInt(7, c.getIdExtern());
        updateStmt.setInt(8, c.getId());
        updateStmt.executeUpdate();
    }

    private Club getClub(ResultSet rs) throws SQLException {
        return new Club(
                rs.getInt("club_id"),
                rs.getString("name"),
                rs.getString("nameShort"),
                rs.getString("nameEnglish"),
                rs.getString("nameEnglishShort"),
                rs.getString("code"),
                rs.getString("nationality"),
                rs.getInt("extern_id")
        );
    }
}
