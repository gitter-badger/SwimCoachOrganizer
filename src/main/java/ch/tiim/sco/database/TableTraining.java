package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Training;

import java.util.List;

public interface TableTraining {
    void addTraining(Training t) throws Exception;

    void updateTraining(Training t) throws Exception;

    void deleteTraining(Training t) throws Exception;

    List<Training> getAllTrainings() throws Exception;
}
