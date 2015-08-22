package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.CLUB_CONTENT;
import static ch.tiim.sco.database.jooq.Tables.TEAM;

public class TableClubContent extends Table {

    protected TableClubContent(DatabaseController db) {
        super(db);
    }

    public void addTeam(Club c, Team t) throws SQLException {
        db.getDsl().insertInto(CLUB_CONTENT, CLUB_CONTENT.CLUB_ID, CLUB_CONTENT.TEAM_ID)
                .values(c.getId(), t.getId()).execute();
    }

    public void deleteTeam(Club c, Team t) throws SQLException {
        db.getDsl().delete(CLUB_CONTENT)
                .where(CLUB_CONTENT.CLUB_ID.equal(c.getId())
                        .and(CLUB_CONTENT.TEAM_ID.equal(t.getId())))
                .execute();
    }

    public List<Team> getTeams(Club c) throws SQLException {
        return db.getDsl().select(TEAM.TEAM_ID, TEAM.NAME)
                .from(CLUB_CONTENT)
                .join(TEAM).onKey()
                .where(CLUB_CONTENT.CLUB_ID.equal(c.getId()))
                .fetch().into(Team.class);
    }

    public List<Team> getNotTeams(Club c) throws SQLException {
        return db.getDsl().select(TEAM.TEAM_ID, TEAM.NAME)
                .from(TEAM)
                .whereNotExists(
                        DSL.selectOne()
                                .from(CLUB_CONTENT)
                                .where(
                                        CLUB_CONTENT.TEAM_ID.equal(TEAM.TEAM_ID)
                                                .and(CLUB_CONTENT.CLUB_ID.equal(c.getId()))
                                )
                ).fetch().into(Team.class);
    }
}
