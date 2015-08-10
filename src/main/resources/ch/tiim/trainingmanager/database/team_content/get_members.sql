SELECT
team_member.member_id,
team_member.first_name,
team_member.last_name,
team_member.birthday,
team_member.address,
team_member.phone_private,
team_member.phone_work,
team_member.phone_mobile,
team_member.email,
team_member.license,
team_member.is_female,
team_member.notes
FROM team_content
    INNER JOIN team_member
        USING(member_id)
WHERE team_id=?;