SELECT
team.team_id,
team.name
FROM team
WHERE NOT EXISTS (
    SELECT 1
    FROM club_content
    WHERE club_content.team_id = team.team_id AND club_content.club_id = ?
);

