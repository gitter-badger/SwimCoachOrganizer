package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Swimmer;

import java.time.LocalDate;
import java.util.List;

public interface TableSwimmer {
    void addSwimmer(Swimmer m) throws Exception;

    void deleteSwimmer(Swimmer m) throws Exception;

    void updateSwimmer(Swimmer m) throws Exception;

    List<Swimmer> getSwimmersWithBirthdayBetween(LocalDate begin, LocalDate end) throws Exception;

    List<Swimmer> getAllSwimmers() throws Exception;
}
