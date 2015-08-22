package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;

import java.sql.SQLException;
import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM;

public class TableTeam extends Table {
    TableTeam(DatabaseController db) {
        super(db);
    }

    public void deleteTeam(Team t) throws SQLException {
        db.getDsl().newRecord(TEAM, t).delete();
    }

    public void addTeam(Team t) throws SQLException {
        db.getDsl().newRecord(TEAM, t).insert();
    }

    public void editTeam(Team t) throws SQLException {
        db.getDsl().newRecord(TEAM, t).update();
    }

    public List<Team> getAllTeams() throws SQLException {
        return db.getDsl().select().from(TEAM).fetch().into(Team.class);
    }
}
