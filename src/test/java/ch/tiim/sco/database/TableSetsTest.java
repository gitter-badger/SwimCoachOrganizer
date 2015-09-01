package ch.tiim.sco.database;

import ch.tiim.sco.database.jooq.Tables;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.SetFocus;
import ch.tiim.sco.database.model.SetForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("SpellCheckingInspection")
public class TableSetsTest {
    private static final Logger LOGGER = LogManager.getLogger(TableSetsTest.class.getName());
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

    //Tests bug fixed in 6765ece006e5e70fc0ad479f78a2066f4f7db932
    @Test
    public void testGetAllSets() {
        SetFocus sf = new SetFocus("Test focus", "tf", "Focus notes");
        SetForm sfo = new SetForm("Test form", "tfo", "Form Notes");
        db.getTblSetFocus().addSetFocus(sf);
        db.getTblSetForm().addSetForm(sfo);
        Set s1 = set();
        s1.setFocus(sf);
        s1.setForm(sfo);
        sets.addSet(s1);

        Assert.assertEquals(s1, sets.getAllSets().get(0));
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
