package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.TeamMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableTeamMember extends Table {


    private PreparedStatement getAllMembersStmt;
    private PreparedStatement addMemberStmt;
    private PreparedStatement deleteMemberStmt;
    private PreparedStatement updateMemberStmt;


    protected TableTeamMember(DatabaseController db) {
        super(db);
    }


    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("TEAM-MEMBER_make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getAllMembersStmt = db.getStmtFile("TEAM-MEMBER_get_all.sql");
        addMemberStmt = db.getStmtFile("TEAM-MEMBER_add.sql");
        deleteMemberStmt = db.getStmtFile("TEAM-MEMBER_delete.sql");
        updateMemberStmt = db.getStmtFile("TEAM-MEMBER_update.sql");
    }

    public List<TeamMember> getAllMembers() throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        ResultSet rs = getAllMembersStmt.executeQuery();
        while (rs.next()) {
            String birthday = rs.getString("birthday");

            TeamMember m = new TeamMember(
                    rs.getInt("member_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    LocalDate.parse(birthday),
                    rs.getString("address"),
                    rs.getString("phone_private"),
                    rs.getString("phone_work"),
                    rs.getString("phone_mobile"),
                    rs.getString("email"),
                    rs.getString("license"),
                    rs.getBoolean("is_female"),
                    rs.getString("notes")
            );
            members.add(m);
        }
        return members;
    }

    public void addMember(TeamMember m) throws SQLException {
        addMemberStmt.setString(1, m.getFirstName());
        addMemberStmt.setString(2, m.getLastName());
        addMemberStmt.setString(3, m.getBirthDay().toString());
        addMemberStmt.setString(4, m.getAddress());
        addMemberStmt.setString(5, m.getPhonePrivate());
        addMemberStmt.setString(6, m.getPhoneWork());
        addMemberStmt.setString(7, m.getPhoneMobile());
        addMemberStmt.setString(8, m.getEmail());
        addMemberStmt.setString(9, m.getLicense());
        addMemberStmt.setBoolean(10, m.isFemale());
        addMemberStmt.setString(11, m.getNotes());
        addMemberStmt.executeUpdate();
    }

    public void deleteMember(TeamMember m) throws SQLException {
        deleteMemberStmt.setInt(1, m.getId());
        deleteMemberStmt.executeUpdate();
    }

    public void updateMember(TeamMember m) throws SQLException {
        updateMemberStmt.setString(1, m.getFirstName());
        updateMemberStmt.setString(2, m.getLastName());
        updateMemberStmt.setString(3, m.getBirthDay().toString());
        updateMemberStmt.setString(4, m.getAddress());
        updateMemberStmt.setString(5, m.getPhonePrivate());
        updateMemberStmt.setString(6, m.getPhoneWork());
        updateMemberStmt.setString(7, m.getPhoneMobile());
        updateMemberStmt.setString(8, m.getEmail());
        updateMemberStmt.setString(9, m.getLicense());
        updateMemberStmt.setBoolean(10, m.isFemale());
        updateMemberStmt.setString(11, m.getNotes());
        updateMemberStmt.setInt(12, m.getId());
        updateMemberStmt.executeUpdate();
    }
}
