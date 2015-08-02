SELECT
member.member_id,
member.first_name,
member.last_name,
member.birthday,
member.address,
member.phone_private,
member.phone_work,
member.phone_mobile,
member.email,
member.license,
member.is_female,
member.notes
FROM team_content
    INNER JOIN member
        USING(member_id)
WHERE team_id=?;