package ch.tiim.trainingmanager.database;

import java.sql.SQLException;

public class TableTeamMember extends Table {


    protected TableTeamMember(DatabaseController db) {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TEAM-MEMBER_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {

    }
}
