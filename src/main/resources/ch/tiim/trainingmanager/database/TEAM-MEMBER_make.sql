CREATE TABLE team_member (
    member_id INTEGER PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    birthday TEXT NOT NULL,
    address TEXT,
    phone_private TEXT,
    phone_work TEXT,
    phone_mobile TEXT,
    email TEXT,
    license TEXT,
    is_female BOOLEAN, -- decided by fair coin flip
    notes TEXT
);