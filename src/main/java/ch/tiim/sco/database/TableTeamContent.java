package ch.tiim.sco.database;

import ch.tiim.sco.database.model.Swimmer;
import ch.tiim.sco.database.model.Team;

import java.util.List;

public interface TableTeamContent {
    List<Swimmer> getMembers(Team t)throws Exception;

    void addMember(Team t, Swimmer m)throws Exception;

    void deleteMember(Team t, Swimmer m)throws Exception;

    List<Swimmer> getNotMembers(Team t)throws Exception;
}
