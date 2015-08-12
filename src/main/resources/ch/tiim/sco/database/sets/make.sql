CREATE TABLE sets (
    set_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    content TEXT,
    distance_f1 INTEGER,
    distance_f2 INTEGER,
    distance_f3 INTEGER NOT NULL,
    intensity INTEGER,
    focus_id INTEGER,
    form_id INTEGER,
    notes TEXT,
    interval INTEGER,
    is_pause BOOLEAN NOT NULL,
    FOREIGN KEY(focus_id) REFERENCES set_focus(focus_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY(form_id) REFERENCES set_form(form_id) ON DELETE RESTRICT ON UPDATE CASCADE
);