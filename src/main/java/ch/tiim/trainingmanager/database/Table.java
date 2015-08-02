package ch.tiim.trainingmanager.database;


import java.sql.SQLException;

public interface Table {

    void mkTable() throws SQLException;
    void loadStatements() throws SQLException;
}
