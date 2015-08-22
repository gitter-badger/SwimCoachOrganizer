package ch.tiim.sco.database;

import ch.tiim.sco.database.model.TeamMember;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM_MEMBER;

public class TableTeamMember extends Table {


    protected TableTeamMember(DatabaseController db) {
        super(db);
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

    public void addMember(TeamMember m) throws SQLException {
        db.getDsl().newRecord(TEAM_MEMBER, m).insert();
    }

    public void deleteMember(TeamMember m) throws SQLException {
        db.getDsl().newRecord(TEAM_MEMBER, m).delete();
    }

    public void updateMember(TeamMember m) throws SQLException {
        db.getDsl().newRecord(TEAM_MEMBER, m).update();
    }

    public List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) throws SQLException {
        return db.getDsl().select()
                .from(TEAM_MEMBER)
                .where(TEAM_MEMBER.BIRTHDAY.ge(begin.toString()).and(TEAM_MEMBER.BIRTHDAY.le(end.toString())))
                .fetch().into(TeamMember.class);
    }

    public void export(Path p) throws IOException, SQLException {
        throw new RuntimeException("NotImplemented");
    }

    public List<TeamMember> getAllMembers() throws SQLException {
        return db.getDsl().select().from(TEAM_MEMBER).fetch().into(TeamMember.class);
    }
}
