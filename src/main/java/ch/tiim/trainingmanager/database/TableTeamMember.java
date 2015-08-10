package ch.tiim.trainingmanager.database;

import ch.tiim.trainingmanager.database.model.TeamMember;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TableTeamMember extends Table {


    private PreparedStatement getAllMembersStmt;
    private PreparedStatement addMemberStmt;
    private PreparedStatement deleteMemberStmt;
    private PreparedStatement updateMemberStmt;
    private PreparedStatement getMembersWithBirthdayBetweenStmt;


    protected TableTeamMember(DatabaseController db) {
        super(db);
    }


    @Override
    public void mkTable() throws SQLException {
        db.getStatement().executeUpdate(db.getSql("team_member/make.sql"));
    }

    @Override
    public void loadStatements() throws SQLException {
        getAllMembersStmt = db.getStmtFile("team_member/get_all.sql");
        addMemberStmt = db.getStmtFile("team_member/add.sql");
        deleteMemberStmt = db.getStmtFile("team_member/delete.sql");
        updateMemberStmt = db.getStmtFile("team_member/update.sql");
        getMembersWithBirthdayBetweenStmt = db.getStmtFile("team_member/get_with_birthday.sql");
    }

    public List<TeamMember> getAllMembers() throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        ResultSet rs = getAllMembersStmt.executeQuery();
        while (rs.next()) {
            TeamMember m = getMember(rs);
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

    static TeamMember getMember(ResultSet rs) throws SQLException {
        return new TeamMember(
                rs.getInt("member_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                LocalDate.parse(rs.getString("birthday")),
                rs.getString("address"),
                rs.getString("phone_private"),
                rs.getString("phone_work"),
                rs.getString("phone_mobile"),
                rs.getString("email"),
                rs.getString("license"),
                rs.getBoolean("is_female"),
                rs.getString("notes"));
    }

    public List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) throws SQLException {
        List<TeamMember> members = new ArrayList<>();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM-dd");
        getMembersWithBirthdayBetweenStmt.setString(1, begin.format(f));
        getMembersWithBirthdayBetweenStmt.setString(2, end.format(f));
        ResultSet rs = getMembersWithBirthdayBetweenStmt.executeQuery();
        while (rs.next()) {
            members.add(getMember(rs));
        }
        return members;
    }

    public void export(Path p) throws IOException, SQLException {
        Files.deleteIfExists(p);
        try {
            db.attach(p);
            db.getStatement().executeUpdate(db.getSql("team_member/export.sql"));
        } finally {
            db.detach();
        }
    }
}
