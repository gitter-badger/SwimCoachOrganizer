package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;

import java.util.List;

public interface TableClub {
    void addClub(Club c) throws Exception;

    void deleteClub(Club c) throws Exception;

    void updateClub(Club c) throws Exception;

    List<Club> getAll() throws Exception;
}
