package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM;

public class TableTeam extends Table {
    TableTeam(DatabaseController db) {
        super(db);
    }

    public void deleteTeam(Team t) {
        db.getDsl().newRecord(TEAM, t).delete();
    }

    public void addTeam(Team t) {
        db.getDsl().newRecord(TEAM, t).insert();
    }

    public void editTeam(Team t) {
        db.getDsl().newRecord(TEAM, t).update();
    }

    public List<Team> getAllTeams() {
        return db.getDsl().select().from(TEAM).fetch().into(Team.class);
    }
}
