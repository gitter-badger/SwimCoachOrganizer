SELECT
member_id,
first_name,
last_name,
birthday,
address,
phone_private,
phone_work,
phone_mobile,
email,
license,
is_female,
notes
FROM team_member
WHERE strftime('%m-%d', birthday) BETWEEN ? AND ?;