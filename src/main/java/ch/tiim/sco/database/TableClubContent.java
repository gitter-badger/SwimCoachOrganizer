package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.util.LinkedList;
import java.util.List;

public class TableClubContent extends Table {

    protected TableClubContent(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public void addTeam(Club c, Team t) {
    }

    public void deleteTeam(Club c, Team t) {
    }

    public List<Team> getTeams(Club c) {
        return new LinkedList<>();
    }

    public List<Team> getNotTeams(Club c) {
        return new LinkedList<>();
    }
}
