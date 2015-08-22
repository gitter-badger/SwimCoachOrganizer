package ch.tiim.sco.database;

import ch.tiim.sco.database.model.TeamMember;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM_MEMBER;

public class TableTeamMember extends Table {


    protected TableTeamMember(DatabaseController db) {
        super(db);
    }

    public void addMember(TeamMember m) {
        db.getDsl().newRecord(TEAM_MEMBER, m).insert();
    }

    public void deleteMember(TeamMember m) {
        db.getDsl().newRecord(TEAM_MEMBER, m).delete();
    }

    public void updateMember(TeamMember m) {
        db.getDsl().newRecord(TEAM_MEMBER, m).update();
    }

    public List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) {
        return db.getDsl().select()
                .from(TEAM_MEMBER)
                .where(TEAM_MEMBER.BIRTHDAY.ge(begin.toString()).and(TEAM_MEMBER.BIRTHDAY.le(end.toString())))
                .fetch().into(TeamMember.class);
    }

    public void export(Path p) throws IOException, SQLException {
        throw new RuntimeException("NotImplemented");
    }

    public List<TeamMember> getAllMembers() {
        return db.getDsl().select().from(TEAM_MEMBER).fetch().into(TeamMember.class);
    }
}
