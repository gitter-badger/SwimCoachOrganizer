UPDATE
    sets
SET
    name = ?,
    content = ?,
    distance_f1 = ?,
    distance_f2 = ?,
    distance_f3 = ?,
    intensity = ?,
    focus_id = ?,
    form_id = ?,
    notes = ?,
    interval = ?,
    is_pause = ?
WHERE
    set_id = ?;