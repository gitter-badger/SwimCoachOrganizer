package ch.tiim.trainingmanager.database;

import ch.tiim.log.Log;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DatabaseController implements Closeable {
    private static final Log LOGGER = new Log(DatabaseController.class);
    private static final int VERSION = 1;

    private final TableSetFocus tblSetFocus;
    private final TableSetForm tblSetForm;
    private final TableTraining tblTraining;
    private final TableTrainingContent tblTrainingContent;
    private final TableTeam tblTeam;
    private final TableTeamContent tblTeamContent;
    private final TableTeamMember tblTeamMember;

    private final TableSets tblSet;
    private final Connection conn;
    private final Path filePath;
    private final PreparedStatement attach;
    private final PreparedStatement detach;

    public DatabaseController(String file) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("org.sqlite.JDBC not found!");
        }
        filePath = Paths.get(file);
        boolean notExists = !Files.exists(filePath);
        conn = DriverManager.getConnection("jdbc:sqlite:" + file);

        if (notExists) {
            conn.createStatement().executeUpdate("PRAGMA user_version = " + VERSION);
        }
        conn.createStatement().executeUpdate("PRAGMA foreign_keys = ON");
        List<Table> tables = new ArrayList<>();

        tables.addAll(Arrays.asList(
                tblSetFocus = new TableSetFocus(this),
                tblSetForm = new TableSetForm(this),
                tblTraining = new TableTraining(this),
                tblSet = new TableSets(this),
                tblTrainingContent = new TableTrainingContent(this),
                tblTeamContent = new TableTeamContent(this),
                tblTeamMember = new TableTeamMember(this),
                tblTeam = new TableTeam(this)
        ));
        try {
            if (notExists) {
                for (Table t : tables) t.mkTable();
            }
        } catch (SQLException e) {
            LOGGER.warning(e);
            conn.close();
            try {
                Files.delete(Paths.get(file));
            } catch (IOException ex) {
                LOGGER.warning(ex);
            }
        }
        for (Table t : tables) t.loadStatements();
        attach = getStmtFile("attach.sql");
        detach = getStmtFile("detach.sql");
    }

    void attach(Path p) throws SQLException {
        attach.setString(1,p.toString());
        attach.executeUpdate();
    }

    void detach() throws SQLException {
        detach.executeUpdate();
    }

    PreparedStatement getStmtFile(String file) throws SQLException {
        return getStatement(getSql(file));
    }

    PreparedStatement getStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    Statement getStatement() throws SQLException {
        return conn.createStatement();
    }

    String getSql(String name) {
        try {
            URI uri = DatabaseController.class.getResource(name).toURI();
            Path p;
            final String[] array = uri.toString().split("!");
            try (FileSystem fs = array.length == 1 ? null :
                    FileSystems.newFileSystem(URI.create(array[0]), new HashMap<>())) {
                if (array.length == 1) {
                    p = Paths.get(uri);
                } else {
                    p = fs.getPath(array[1]);
                }
                return new String(Files.readAllBytes(p));
            }
        } catch (URISyntaxException | IOException e) {
            LOGGER.warning(e);
        }
        return "";
    }

    public TableSetFocus getTblSetFocus() {
        return tblSetFocus;
    }

    public TableSetForm getTblSetForm() {
        return tblSetForm;
    }

    public TableTraining getTblTraining() {
        return tblTraining;
    }

    public TableSets getTblSet() {
        return tblSet;
    }

    public TableTeam getTblTeam() {
        return tblTeam;
    }

    public TableTeamContent getTblTeamContent() {
        return tblTeamContent;
    }

    public TableTeamMember getTblTeamMember() {
        return tblTeamMember;
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public TableTrainingContent getTblTrainingContent() {
        return tblTrainingContent;
    }

    public void exportAll(Path p) throws IOException {
        Files.copy(filePath,p, StandardCopyOption.REPLACE_EXISTING);
    }
}
