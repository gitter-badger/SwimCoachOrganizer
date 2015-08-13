package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TableSetsTest {

    private DatabaseController db;
    private TableSets sets;

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
        Set s = new Set(
                -1,
                "TestName",
                "Content",
                1, 2, 3, 99,
                null, null,
                "Test Note",
                10, true
        );
        sets.addSet(s);
        Assert.assertEquals(s, sets.getAllSets().get(0));
    }
}
