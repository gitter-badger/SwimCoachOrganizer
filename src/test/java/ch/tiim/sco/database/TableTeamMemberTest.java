package ch.tiim.sco.database;

import ch.tiim.sco.database.model.TeamMember;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableTeamMemberTest {

    private DatabaseController db;

    @Before
    public void setUp() throws Exception {
        db = new DatabaseController(":memory:");
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testAddMember() throws Exception {
        TeamMember m = member();
        db.getTblTeamMember().addMember(m);
        assertEquals(m, db.getTblTeamMember().getAllMembers().get(0));
    }

    @Test
    public void testDeleteMember() throws Exception {
        TeamMember m = member();
        db.getTblTeamMember().addMember(m);
        db.getTblTeamMember().deleteMember(m);
        assertEquals(0, db.getTblTeamMember().getAllMembers().size());
    }

    @Test
    public void testUpdateMember() throws Exception {
        TeamMember m = member();
        db.getTblTeamMember().addMember(m);
        m.setFirstName("New First Name");
        db.getTblTeamMember().updateMember(m);
        assertEquals(m, db.getTblTeamMember().getAllMembers().get(0));
    }

    @Test
    public void testGetMembersWithBirthdayBetween() throws Exception {
        TeamMember m = member();
        m.setBirthDay(LocalDate.of(1995, 1, 1));
        db.getTblTeamMember().addMember(m);
        List<TeamMember> b1 = db.getTblTeamMember().
                getMembersWithBirthdayBetween(LocalDate.of(1994, 1, 1), LocalDate.of(1996, 1, 1));
        assertTrue(b1.contains(m));
    }

    @Test
    public void testGetMembersWithBirthdayBetweenBorderStart() throws Exception {
        TeamMember m = member();
        m.setBirthDay(LocalDate.of(1995, 1, 1));
        db.getTblTeamMember().addMember(m);
        List<TeamMember> b1 = db.getTblTeamMember().
                getMembersWithBirthdayBetween(LocalDate.of(1995, 1, 1), LocalDate.of(1996, 1, 1));
        assertTrue(b1.contains(m));
    }

    @Test
    public void testGetMembersWithBirthdayBetweenBorderEnd() throws Exception {
        TeamMember m = member();
        m.setBirthDay(LocalDate.of(1995, 1, 1));
        db.getTblTeamMember().addMember(m);
        List<TeamMember> b1 = db.getTblTeamMember().
                getMembersWithBirthdayBetween(LocalDate.of(1994, 1, 1), LocalDate.of(1995, 1, 1));
        assertTrue(b1.contains(m));
    }

    @Test
    public void testGetAllMembers() throws Exception {
        db.getTblTeamMember().addMember(member());
        db.getTblTeamMember().addMember(member());
        db.getTblTeamMember().addMember(member());
        db.getTblTeamMember().addMember(member());
        assertEquals(4, db.getTblTeamMember().getAllMembers().size());
    }

    private TeamMember member() {
        return new TeamMember(
                "FirstName",
                "LastName",
                LocalDate.of(1996, 1, 26),
                "Address",
                "123 234 345",
                null,
                null,
                "email@mail.com",
                null,
                false,
                "Notes"
        );
    }

}