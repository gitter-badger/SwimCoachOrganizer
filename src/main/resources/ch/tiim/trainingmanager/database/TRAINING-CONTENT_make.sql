CREATE TABLE training_content (
    training_id INTEGER NOT NULL,
    set_id INTEGER NOT NULL,
    indx INTEGER NOT NULL,
    FOREIGN KEY(training_id) REFERENCES training(training_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(set_id) REFERENCES sets(set_id) ON DELETE CASCADE ON UPDATE CASCADE
);