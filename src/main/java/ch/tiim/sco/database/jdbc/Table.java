package ch.tiim.sco.database.jdbc;


import ch.tiim.sco.database.DatabaseController;

abstract class Table {

    final DatabaseController db;

    protected Table(DatabaseController db) {
        this.db = db;
        loadStatements();
    }

    protected abstract void loadStatements();
}
