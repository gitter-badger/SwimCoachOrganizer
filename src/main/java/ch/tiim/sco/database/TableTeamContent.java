package ch.tiim.sco.database;

import ch.tiim.sco.database.mapper.LocalDateConverter;
import ch.tiim.sco.database.model.Team;
import ch.tiim.sco.database.model.TeamMember;
import org.jooq.impl.DSL;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM_CONTENT;
import static ch.tiim.sco.database.jooq.Tables.TEAM_MEMBER;

public class TableTeamContent extends Table {

    protected TableTeamContent(DatabaseController db) {
        super(db);
    }

    public List<TeamMember> getMembersForTeam(Team t) {
        return db.getDsl().select()
                .from(TEAM_CONTENT)
                .join(TEAM_MEMBER).onKey()
                .where(TEAM_CONTENT.TEAM_ID.equal(t.getId()))
                .fetch().into(TeamMember.class);
    }

    public void addMemberToTeam(Team t, TeamMember m) {
        db.getDsl().insertInto(TEAM_CONTENT,
                TEAM_CONTENT.TEAM_ID, TEAM_CONTENT.MEMBER_ID)
                .values(t.getId(), m.getId())
                .execute();
    }

    public void removeMemberFromTeam(Team t, TeamMember m) {
        db.getDsl().delete(TEAM_CONTENT)
                .where(TEAM_CONTENT.TEAM_ID.equal(t.getId())
                        .and(TEAM_CONTENT.MEMBER_ID.equal(m.getId())))
                .execute();
    }

    public List<TeamMember> getMembersNotInTeam(Team t) {
        return db.getDsl().select()
                .from(TEAM_MEMBER)
                .whereNotExists(
                        DSL.selectOne()
                                .from(TEAM_CONTENT)
                                .where(TEAM_CONTENT.MEMBER_ID.equal(TEAM_MEMBER.MEMBER_ID)
                                        .and(TEAM_CONTENT.TEAM_ID.equal(t.getId())))
                ).fetch().into(TeamMember.class);
    }
}
