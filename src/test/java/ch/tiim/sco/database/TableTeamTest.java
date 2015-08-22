package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Team;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableTeamTest {

    private DatabaseController db;
    private TableTeam table;
    private Team team;

    @Before
    public void setUp() throws Exception {
        db = new DatabaseController(":memory:");
        table = db.getTblTeam();
        team = new Team("Team Name");
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testGetAllTeams() throws Exception {
        table.addTeam(team);
        table.addTeam(team);
        table.addTeam(team);
        table.addTeam(team);
        Assert.assertEquals(4, table.getAllTeams().size());
    }

    @Test
    public void testEditTeam() throws Exception {
        table.addTeam(team);
        team.setId(1);
        team.setName("New Name");
        table.editTeam(team);
        Team f = table.getAllTeams().get(0);
        Assert.assertEquals(team, f);
    }

    @Test
    public void testDeleteTeam() throws Exception {
        table.addTeam(team);
        Team t = table.getAllTeams().get(0);
        table.deleteTeam(t);
        Assert.assertEquals(0, table.getAllTeams().size());
    }


}