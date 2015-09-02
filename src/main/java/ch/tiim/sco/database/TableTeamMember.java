package ch.tiim.sco.database;

import ch.tiim.sco.database.model.TeamMember;

import java.time.LocalDate;
import java.util.List;

public interface TableTeamMember {
    void addMember(TeamMember m) throws Exception;

    void deleteMember(TeamMember m) throws Exception;

    void updateMember(TeamMember m) throws Exception;

    List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end) throws Exception;

    List<TeamMember> getAllMembers() throws Exception;
}
