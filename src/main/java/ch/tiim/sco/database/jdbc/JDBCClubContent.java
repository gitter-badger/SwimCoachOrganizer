package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;
import ch.tiim.sco.database.model.Team;

import java.util.LinkedList;
import java.util.List;

public class JDBCClubContent extends Table implements ch.tiim.sco.database.TableClubContent {

    public JDBCClubContent(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    @Override
    public void addTeam(Club c, Team t) {
    }

    @Override
    public void deleteTeam(Club c, Team t) {
    }

    @Override
    public List<Team> getTeams(Club c) {
        return new LinkedList<>();
    }

    @Override
    public List<Team> getNotTeams(Club c) {
        return new LinkedList<>();
    }
}
