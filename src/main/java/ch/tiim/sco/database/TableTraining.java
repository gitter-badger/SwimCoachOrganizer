package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Training;

import java.util.List;

public interface TableTraining {
    void addTraining(Training t);

    void updateTraining(Training t);

    void deleteTraining(Training t);

    List<Training> getAllTrainings();
}
