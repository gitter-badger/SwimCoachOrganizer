package ch.tiim.sco.database;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController implements Closeable {
    private static final Logger LOGGER = LogManager.getLogger(DatabaseController.class.getName());
    private static final String VERSION = "1";

    private final TableSetFocus tblSetFocus;
    private final TableSetForm tblSetForm;
    private final TableTraining tblTraining;
    private final TableTrainingContent tblTrainingContent;
    private final TableTeam tblTeam;
    private final TableTeamContent tblTeamContent;
    private final TableTeamMember tblTeamMember;
    private final TableSets tblSet;
    private final TableClub tblClub;
    private final TableClubContent tblClubContent;

    private final Connection conn;
    private final JdbcTemplate jdbc;
    private final Path filePath;
    private boolean initialized = false;

    public DatabaseController(String file) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("org.sqlite.JDBC not found!");
        }

        boolean notExists;
        if (!file.equals(":memory:")) {
            filePath = Paths.get(file);
            notExists = !Files.exists(filePath);
        } else {
            filePath = null;
            notExists = true;
        }

        conn = DriverManager.getConnection("jdbc:sqlite:" + file);

        if (notExists) {
            mkDatabase();
        } else {
            initialized = true;
        }

        SQLiteConfig config = new SQLiteConfig();
        config.setDatePrecision("SECONDS");
        config.setPragma(SQLiteConfig.Pragma.USER_VERSION, VERSION);
        jdbc = new JdbcTemplate(new SQLiteDataSource());

        tblSetFocus = new TableSetFocus(this);
        tblSetForm = new TableSetForm(this);
        tblTraining = new TableTraining(this);
        tblSet = new TableSets(this);
        tblTrainingContent = new TableTrainingContent(this);
        tblTeamContent = new TableTeamContent(this);
        tblTeamMember = new TableTeamMember(this);
        tblTeam = new TableTeam(this);
        tblClub = new TableClub(this);
        tblClubContent = new TableClubContent(this);
    }

    private void mkDatabase() throws SQLException {
        conn.createStatement().executeUpdate("PRAGMA user_version = " + VERSION);
        conn.createStatement().executeUpdate("PRAGMA foreign_keys = ON");
        Statement stmt = conn.createStatement();
        String[] cmds = getSql("make.sql").split(";");
        for (String cmd : cmds) {
            stmt.addBatch(cmd);
        }
        stmt.executeBatch();
    }

    private String getSql(String name) {
        try (InputStreamReader is = new InputStreamReader(
                DatabaseController.class.getResourceAsStream(name), Charsets.UTF_8)) {
            return CharStreams.toString(is);
        } catch (IOException e) {
            LOGGER.warn("Could not load file " + name, e);
        }
        return "";
    }

    public void initializeDefaultValues() throws SQLException {
        if (!initialized) {
            initialized = true;
        } else {
            LOGGER.info("Default values already initialized.. aborting!");
            return;
        }
        Statement stmt = conn.createStatement();
        String[] cmds = getSql("init.sql").split(";");
        for (String cmd : cmds) {
            stmt.addBatch(cmd);
        }
        stmt.executeBatch();
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public TableClub getTblClub() {
        return tblClub;
    }

    public TableClubContent getTblClubContent() {
        return tblClubContent;
    }

    public TableSets getTblSet() {
        return tblSet;
    }

    public TableSetFocus getTblSetFocus() {
        return tblSetFocus;
    }

    public TableSetForm getTblSetForm() {
        return tblSetForm;
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

    public TableTraining getTblTraining() {
        return tblTraining;
    }

    public TableTrainingContent getTblTrainingContent() {
        return tblTrainingContent;
    }
}
