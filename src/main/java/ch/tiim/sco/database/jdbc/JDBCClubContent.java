package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.util.LinkedList;
import java.util.List;

public class JDBCClubContent extends Table {

    public JDBCClubContent(DatabaseController db) {
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
