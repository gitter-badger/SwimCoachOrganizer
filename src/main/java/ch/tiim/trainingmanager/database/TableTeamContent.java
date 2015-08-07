package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.TeamMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableTeamContent extends Table {

    private PreparedStatement getMembersForTeamStmt;

    protected TableTeamContent(DatabaseController db) {
        super(db);
    }

    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TEAM-CONTENT_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getMembersForTeamStmt = db.getStmtFile("TEAM-CONTENT_get_members.sql");
    }

    public List<TeamMember> getMembersForTeam(int teamId) throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        getMembersForTeamStmt.setInt(1, teamId);
        ResultSet rs = getMembersForTeamStmt.executeQuery();
        while (rs.next()) {
            TeamMember m = new TeamMember(
                    rs.getInt("member_id"), rs.getString("first_name"), rs.getString("last_name"),
                    LocalDate.parse(rs.getString("birthday")), rs.getString("address"), rs.getString("phone_private"),
                    rs.getString("phone_work"), rs.getString("phone_mobile"), rs.getString("email"),
                    rs.getString("license"), rs.getBoolean("is_female"), rs.getString("notes")
            ); // Puuhh!
            members.add(m);
        }
        return members;
    }
}
