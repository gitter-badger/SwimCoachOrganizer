package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.TeamMember;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class JDBCTeamMember extends Table {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTeamMember.class.getName());

    public JDBCTeamMember(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public void addMember(TeamMember m) {
    }

    public void deleteMember(TeamMember m) {
    }

    public void updateMember(TeamMember m) {
    }

    public List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) {
        return new LinkedList<>();
    }

    public void export(Path p) throws IOException, SQLException {
        throw new RuntimeException("NotImplemented");
    }

    public List<TeamMember> getAllMembers() {
        return new LinkedList<>();
    }
}
