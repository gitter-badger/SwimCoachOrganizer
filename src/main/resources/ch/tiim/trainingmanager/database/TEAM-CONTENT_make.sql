CREATE TABLE team_content (
    member_id INTEGER,
    team_id INTEGER,
    FOREIGN KEY(member_id) REFERENCES team_member(member_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(team_id) REFERENCES team(team_id) ON DELETE CASCADE ON UPDATE CASCADE
);