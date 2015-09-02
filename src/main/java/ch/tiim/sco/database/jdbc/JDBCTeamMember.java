package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.TeamMember;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class JDBCTeamMember extends Table implements ch.tiim.sco.database.TableTeamMember {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTeamMember.class.getName());

    public JDBCTeamMember(DatabaseController db) throws SQLException {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public void addMember(TeamMember m) {
    }

    @Override
    public void deleteMember(TeamMember m) {
    }

    @Override
    public void updateMember(TeamMember m) {
    }

    @Override
    public List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) {
        return new LinkedList<>();
    }

    @Override
    public List<TeamMember> getAllMembers() {
        return new LinkedList<>();
    }
}
