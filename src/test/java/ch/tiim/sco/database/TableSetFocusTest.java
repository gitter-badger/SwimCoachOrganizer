package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetFocus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableSetFocusTest {

    private DatabaseController db;
    private TableSetFocus table;

    @Before
    public void setUp() throws Exception {
        db = new DatabaseController(":memory:");
        table = db.getTblSetFocus();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testGetSetFocus() throws Exception {
        table.addSetFocus(focus());
        SetFocus f = table.getAllFoci().get(0);
        SetFocus focus = focus();
        focus.setId(f.getId());
        Assert.assertEquals(focus, f);
    }

    @Test
    public void testAddSetFocus() throws Exception {
        table.addSetFocus(focus());
        table.addSetFocus(focus());
        table.addSetFocus(focus());
        table.addSetFocus(focus());
        table.addSetFocus(focus());
        table.addSetFocus(focus());
        Assert.assertEquals(6, table.getAllFoci().size());
    }

    @Test
    public void testUpdateSetFocus() throws Exception {
        SetFocus f = focus();
        table.addSetFocus(f);
        f.setName("New Name");
        table.updateSetFocus(f);
        SetFocus fo = table.getAllFoci().get(0);
        Assert.assertEquals(f, fo);
    }

    @Test
    public void testDeleteSetFocus() throws Exception {
        table.addSetFocus(focus());
        table.deleteSetFocus(table.getAllFoci().get(0));
        Assert.assertEquals(0, table.getAllFoci().size());
    }

    private SetFocus focus() {
        return new SetFocus("Sprint", "Spr", "Swim fast!");
    }
}