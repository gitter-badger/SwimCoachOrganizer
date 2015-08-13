package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("SpellCheckingInspection")
public class TableSetsTest {

    private Set set;

    private DatabaseController db;
    private TableSets sets;

    @Before
    public void setup() throws SQLException {
        set = new Set(
                -1,
                "TestName",
                "Content",
                1, 2, 3, 99,
                null, null,
                "Test Note",
                10, true
        );
        db = new DatabaseController(":memory:");
        sets = db.getTblSet();
    }

    @After
    public void tearDown() throws IOException {
        db.close();
    }

    @Test
    public void testInsert() throws SQLException {
        sets.addSet(set);
        Assert.assertEquals(set, sets.getAllSets().get(0));
    }

    @Test
    public void testUpdate() throws SQLException {
        sets.addSet(set);
        Set s = sets.getAllSets().get(0);
        s.setContent("123Test");
        sets.updateSet(s);
        Set s2 = sets.getAllSets().get(0);
        s2.setId(set.getId()); // Id has to change
        Assert.assertEquals(s, s2);
    }

    @Test
    public void testUpdateUnicode() throws SQLException {
        sets.addSet(set);
        Set s = sets.getAllSets().get(0);
        s.setContent("\uD83D\uDE35 -- This is an emoji xoxo");
        sets.updateSet(s);
        Set s2 = sets.getAllSets().get(0);
        Assert.assertEquals(s, s2);
    }
}
