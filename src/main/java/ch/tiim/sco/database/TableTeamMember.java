package ch.tiim.sco.database;

import ch.tiim.sco.database.model.TeamMember;

import java.time.LocalDate;
import java.util.List;

public interface TableTeamMember {
    void addMember(TeamMember m);

    void deleteMember(TeamMember m);

    void updateMember(TeamMember m);

    List<TeamMember> getMembersWithBirthdayBetween(LocalDate begin, LocalDate end);

    List<TeamMember> getAllMembers();
}
