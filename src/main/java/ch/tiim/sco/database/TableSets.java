package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Set;

import java.util.List;

public interface TableSets {
    void addSet(Set set)throws Exception;

    void updateSet(Set set)throws Exception;

    void deleteSet(Set set)throws Exception;

    List<Set> getAllSets()throws Exception;
}
