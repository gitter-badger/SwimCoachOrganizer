package ch.tiim.sco.database;

import ch.tiim.sco.database.model.SetForm;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableSetFormTest {

    private DatabaseController db;
    private TableSetForm table;
    private SetForm form;

    @Before
    public void setUp() throws Exception {
        form = new SetForm("Freestyle", "Fr", "blub blub");
        db = new DatabaseController(":memory:");
        table = db.getTblSetForm();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testGetSetForm() throws Exception {
        table.addSetForm(form);
        SetForm setForm = table.getAllForms().get(0);
        form.setId(setForm.getId());
        assertEquals(form, setForm);
    }

    @Test
    public void testAddSetForm() throws Exception {
        table.addSetForm(form);
        table.addSetForm(form);
        table.addSetForm(form);
        table.addSetForm(form);
        table.addSetForm(form);
        table.addSetForm(form);
        assertEquals(6, table.getAllForms().size());
    }

    @Test
    public void testUpdateSetForm() throws Exception {
        table.addSetForm(form);
        SetForm setForm = table.getAllForms().get(0);
        setForm.setName("sdflnk asd");
        table.updateSetForm(setForm);
        SetForm setForm1 = table.getAllForms().get(0);
        assertEquals(setForm, setForm1);
    }

    @Test
    public void testDeleteSetForm() throws Exception {
        table.addSetForm(form);
        table.deleteSetForm(table.getAllForms().get(0));
        assertEquals(0, table.getAllForms().size());
    }
}