package ch.tiim.sco.database.jdbc;

import ch.tiim.jdbc.namedparameters.NamedParameterPreparedStatement;
import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCClub extends Table implements ch.tiim.sco.database.TableClub {


    private NamedParameterPreparedStatement addStmt;
    private NamedParameterPreparedStatement deleteStmt;
    private NamedParameterPreparedStatement updateStmt;
    private NamedParameterPreparedStatement getAllStmt;


    public JDBCClub(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() throws SQLException {
        addStmt = db.getPrepStmt(getSql("add"));
        deleteStmt = db.getPrepStmt(getSql("delete"));
        updateStmt = db.getPrepStmt(getSql("update"));
        getAllStmt = db.getPrepStmt(getSql("get_all"));
    }

    @Override
    public void addClub(Club c) throws SQLException {
        addStmt.setString("name", c.getName());
        addStmt.setString("nameShort", c.getNameShort());
        addStmt.setString("nameEn", c.getNameEn());
        addStmt.setString("nameShortEn", c.getNameShortEn());
        addStmt.setString("code", c.getCode());
        addStmt.setString("nationality", c.getNationality());
        addStmt.setInt("externId", c.getExternId());
        testUpdate(addStmt.executeUpdate());
        c.setId(getGenKey(addStmt));
    }

    @Override
    public void deleteClub(Club c) throws SQLException {
        deleteStmt.setInt("id", c.getId());
        testUpdate(deleteStmt.executeUpdate());
    }

    @Override
    public void updateClub(Club c) throws SQLException {
        updateStmt.setString("name", c.getName());
        updateStmt.setString("nameShort", c.getNameShort());
        updateStmt.setString("nameEn", c.getNameEn());
        updateStmt.setString("nameShortEn", c.getNameShortEn());
        updateStmt.setString("code", c.getCode());
        updateStmt.setString("nationality", c.getNationality());
        updateStmt.setInt("externId", c.getExternId());
        updateStmt.setInt("id", c.getId());
        testUpdate(updateStmt.executeUpdate());
    }

    @Override
    public List<Club> getAll() throws SQLException {
        ResultSet rs = getAllStmt.executeQuery();
        List<Club> l = new ArrayList<>(rs.getFetchSize());
        while (rs.next()) {
            l.add(getClub(rs));
        }
        return l;
    }

    Club getClub(ResultSet rs) throws SQLException {
        return new Club(
                rs.getInt("club_id"),
                rs.getString("name"),
                rs.getString("name_short"),
                rs.getString("name_en"),
                rs.getString("name_short_en"),
                rs.getString("code"),
                rs.getString("nationality"),
                rs.getInt("extern_id")
        );
    }
}
