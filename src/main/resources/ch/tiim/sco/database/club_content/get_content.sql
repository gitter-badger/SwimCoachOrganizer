SELECT
team.team_id,
team.name
FROM club_content
    INNER JOIN team
        USING(team_id)
WHERE club_id=?;