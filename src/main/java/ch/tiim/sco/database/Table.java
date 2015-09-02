package ch.tiim.sco.database;


abstract class Table {

    final DatabaseController db;

    protected Table(DatabaseController db) {
        this.db = db;
        loadStatements();
    }

    protected abstract void loadStatements();
}
