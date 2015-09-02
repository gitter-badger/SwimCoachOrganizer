package ch.tiim.sco.database;

import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import ch.tiim.sco.database.model.Training;

import java.util.List;

public interface TableTrainingContent {
    List<IndexedSet> getSetsForTraining(Training training);

    void addSetToTraining(Training t, Set set, int index);

    void deleteSet(Training t, Set s, int index);

    void updateIndex(Training tr, int index, boolean up);
}
