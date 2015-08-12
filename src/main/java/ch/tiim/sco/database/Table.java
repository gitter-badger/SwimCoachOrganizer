package ch.tiim.sco.database;


import java.sql.SQLException;

abstract class Table {

    final DatabaseController db;

    protected Table(DatabaseController db) {
        this.db = db;
    }

    abstract void mkTable() throws SQLException;
    abstract void loadStatements() throws SQLException;
}
