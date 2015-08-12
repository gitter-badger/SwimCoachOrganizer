SELECT
sets.set_id,
sets.name,
sets.content,
sets.distance_f1,
sets.distance_f2,
sets.distance_f3,
sets.intensity,
sets.notes,
sets.interval,
sets.is_pause,

sets.focus_id,
set_focus.name AS focus_name,
set_focus.abbr AS focus_abbr,
set_focus.notes AS focus_notes,
sets.form_id,
set_form.name AS form_name,
set_form.abbr AS form_abbr,
set_form.notes AS form_notes

FROM sets
    LEFT OUTER JOIN set_focus
        USING (focus_id)
    LEFT OUTER JOIN set_form
        USING (form_id);