package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Club;

import java.util.List;

public interface TableClub {
    void addClub(Club c);

    void deleteClub(Club c);

    void updateClub(Club c);

    List<Club> getAll();
}
