package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Training;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TableTrainingTest {

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
    public void testAddTraining() throws Exception {
        Training t = training();
        db.getTblTraining().addTraining(t);
        List<Training> all = db.getTblTraining().getAllTrainings();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(t, all.get(0));
    }

    @Test
    public void testUpdateTraining() throws Exception {
        Training t = training();
        db.getTblTraining().addTraining(t);
        t.setName("New Name");
        db.getTblTraining().updateTraining(t);
        List<Training> all = db.getTblTraining().getAllTrainings();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(t, all.get(0));
    }

    @Test
    public void testDeleteTraining() throws Exception {
        Training t = training();
        Training t2 = training();
        db.getTblTraining().addTraining(t);
        db.getTblTraining().addTraining(t2);
        db.getTblTraining().deleteTraining(t);
        List<Training> all = db.getTblTraining().getAllTrainings();
        Assert.assertEquals(1, all.size());
        Assert.assertEquals(t2, all.get(0));
    }

    @Test
    public void testGetAllTrainings() throws Exception {
        db.getTblTraining().addTraining(training());
        db.getTblTraining().addTraining(training());
        db.getTblTraining().addTraining(training());
        db.getTblTraining().addTraining(training());
        Assert.assertEquals(4, db.getTblTraining().getAllTrainings().size());
    }

    private Training training() {
        return new Training("Training Name");
    }
}