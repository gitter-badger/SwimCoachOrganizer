package ch.tiim.sco.database;

import ch.tiim.log.Log;
import ch.tiim.sco.database.mapper.RecordMapperProviderImpl;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

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
    private final TableClub tblClub;
    private final TableClubContent tblClubContent;

    private final Connection conn;
    private final DSLContext create;
    private final Path filePath;

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
        create = DSL.using(new DefaultConfiguration()
                        .set(conn)
                        .set(SQLDialect.SQLITE)
                        .set(new RecordMapperProviderImpl())
                        .set(new Settings().withExecuteLogging(true))
        );


        if (notExists) {
            mkDatabase();
        }

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
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    DSLContext getDsl() {
        return create;
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
