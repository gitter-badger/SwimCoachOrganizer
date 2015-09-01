-- Every column that stores LocalDates (YYYY-MM-DD) must have a name matching .*_day


CREATE TABLE club (
    club_id INTEGER PRIMARY KEY,
    name TEXT,
    name_short TEXT,
    name_en TEXT,
    name_short_en TEXT,
    code TEXT,
    nationality TEXT,
    extern_id INTEGER
);

CREATE TABLE club_content (
    club_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    FOREIGN KEY(club_id) REFERENCES club(club_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(team_id) REFERENCES team(team_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE set_focus (
    focus_id INTEGER PRIMARY KEY,
    name TEXT,
    abbr TEXT,
    notes TEXT
);

CREATE TABLE set_form (
    form_id INTEGER PRIMARY KEY,
    name TEXT,
    abbr TEXT,
    notes TEXT
);

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

CREATE TABLE team (
    team_id INTEGER PRIMARY KEY,
    name TEXT
);

CREATE TABLE team_content (
    member_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    FOREIGN KEY(member_id) REFERENCES team_member(member_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(team_id) REFERENCES team(team_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE team_member (
    member_id INTEGER PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    birth_day TEXT NOT NULL,
    address TEXT,
    phone_private TEXT,
    phone_work TEXT,
    phone_mobile TEXT,
    email TEXT,
    license TEXT,
    is_female BOOLEAN, -- decided by fair coin flip
    notes TEXT
);

CREATE TABLE training (
    training_id INTEGER PRIMARY KEY,
    name TEXT
);

CREATE TABLE training_content (
    training_id INTEGER NOT NULL,
    set_id INTEGER NOT NULL,
    indx INTEGER NOT NULL,
    FOREIGN KEY(training_id) REFERENCES training(training_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(set_id) REFERENCES sets(set_id) ON DELETE CASCADE ON UPDATE CASCADE --,
    --UNIQUE (training_id, indx)
);