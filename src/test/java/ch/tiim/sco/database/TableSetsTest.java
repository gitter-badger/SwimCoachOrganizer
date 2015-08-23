package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

@SuppressWarnings("SpellCheckingInspection")
public class TableSetsTest {

    private TableSets sets;

    private DatabaseController db;

    @Before
    public void setup() throws SQLException {
        db = new DatabaseController(":memory:");
        sets = db.getTblSet();
    }

    @After
    public void tearDown() throws IOException {
        db.close();
    }

    @Test
    public void testInsert() throws SQLException {
        Set s = set();
        sets.addSet(s);
        Set result = sets.getAllSets().get(0);
        assertEquals(s, result);
    }

    @Test
    public void testUpdate() throws SQLException {
        sets.addSet(set());
        Set s = sets.getAllSets().get(0);
        s.setContent("123Test");
        sets.updateSet(s);
        Set s2 = sets.getAllSets().get(0);
        assertEquals(s, s2);
    }

    @Test
    public void testUpdateUnicode() throws SQLException {
        sets.addSet(set());
        Set s = sets.getAllSets().get(0);
        s.setContent("\uD83D\uDE35 -- This is an emoji xoxo");
        sets.updateSet(s);
        Set s2 = sets.getAllSets().get(0);
        assertEquals(s, s2);
    }

    private Set set() {
        return new Set(
                "TestName",
                "Content",
                1, 2, 3, 99,
                null, null,
                "Test Note",
                10, true
        );
    }
}
