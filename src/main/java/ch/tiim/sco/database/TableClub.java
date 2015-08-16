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
        addStmt = db.getStmtFile("club/get_all.sql");
    }

    public void addClub(Club c) throws SQLException {
        addStmt.setString(1, c.getName());
        addStmt.setString(2, c.getNameShort());
        addStmt.setString(2, c.getNameEnglish());
        addStmt.setString(2, c.getNameEnglishShort());
        addStmt.setString(2, c.getCode());
        addStmt.setString(2, c.getNationality());
        addStmt.setInt(2, c.getIdExtern());
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

    private Club getClub(ResultSet rs) throws SQLException {
        return new Club(
                rs.getInt("id"),
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
