package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.tables.records.TeamRecord;
import ch.tiim.sco.database.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static ch.tiim.sco.database.jooq.Tables.TEAM;

public class TableTeam extends Table {
    private static final Logger LOGGER = LogManager.getLogger(TableTeam.class.getName());

    TableTeam(DatabaseController db) {
        super(db);
    }

    public void deleteTeam(Team t) {
        db.getDsl().newRecord(TEAM, t).delete();
    }

    public void addTeam(Team t) {
        TeamRecord teamRecord = db.getDsl().newRecord(TEAM, t);
        teamRecord.insert();
        t.setId(teamRecord.getTeamId());
    }

    public void editTeam(Team t) {
        db.getDsl().newRecord(TEAM, t).update();
    }

    public List<Team> getAllTeams() {
        return db.getDsl().select().from(TEAM).fetch().into(Team.class);
    }
}
