package ch.tiim.sco.database.jdbc;


import ch.tiim.sco.database.DatabaseController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Table {

    protected final DatabaseController db;

    protected Table(DatabaseController db) throws SQLException {
        this.db = db;
        loadStatements();
    }

    protected abstract void loadStatements() throws SQLException;

    protected String getSql(String name) {
        String module = getClass().getSimpleName().replace("JDBC", "");
        return db.getSqlLoader().getValue(module, name);
    }

    protected void testUpdate(int rows) throws SQLException {
        if (rows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
    }

    protected int getGenKey(PreparedStatement stmt) throws SQLException {
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }
}
