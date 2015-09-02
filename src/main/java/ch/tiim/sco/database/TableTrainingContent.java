package ch.tiim.sco.database;

import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class TableTrainingContent extends Table {
    private static final Logger LOGGER = LogManager.getLogger(TableTrainingContent.class.getName());

    TableTrainingContent(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {

    }

    public List<IndexedSet> getSetsForTraining(Training training) {
        return new LinkedList<>();
    }

    public void addSetToTraining(Training t, Set set, int index) {
    }

    public void deleteSet(Training t, Set s, int index) {
    }

    public void updateIndex(Training tr, int index, boolean up) {
    }
}
