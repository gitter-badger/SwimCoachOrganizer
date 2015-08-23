package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableTeamTest {

    private DatabaseController db;
    private TableTeam table;

    @Before
    public void setUp() throws Exception {
        db = new DatabaseController(":memory:");
        table = db.getTblTeam();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testGetAllTeams() throws Exception {
        table.addTeam(team());
        table.addTeam(team());
        table.addTeam(team());
        table.addTeam(team());
        assertEquals(4, table.getAllTeams().size());
    }

    @Test
    public void testEditTeam() throws Exception {
        Team team = team();
        table.addTeam(team);
        team.setName("New Name");
        table.editTeam(team);
        Team f = table.getAllTeams().get(0);
        assertEquals(team, f);
    }

    @Test
    public void testDeleteTeam() throws Exception {
        table.addTeam(team());
        Team t = table.getAllTeams().get(0);
        table.deleteTeam(t);
        assertEquals(0, table.getAllTeams().size());
    }

    private Team team() {
        return new Team("Team Name");
    }
}