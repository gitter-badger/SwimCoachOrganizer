UPDATE team_member
SET
    first_name=?,
    last_name=?,
    birthday=?,
    address=?,
    phone_private=?,
    phone_work=?,
    phone_mobile=?,
    email=?,
    license=?,
    is_female=?,
    notes=?
WHERE
    member_id=?;