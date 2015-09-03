INSERT INTO set_focus (name, abbr)
VALUES
('Sprint', 'Spr'),
('Endurance', 'End'),
('Recovery', 'Rec');

INSERT INTO set_form (name, abbr)
VALUES
('Butterfly', 'Fly'),
('Back Stroke', 'Ba'),
('Breast Stroke', 'Br'),
('Freestyle', 'Fr'),
('Individual Medley', 'IM');

INSERT INTO sets (name, content, distance_f1, distance_f2, distance_f3, intensity, focus_id, form_id, notes, interval, is_pause)
VALUES
('Crawl Pull/Kick', 'First pull, second kick', 1, 2, 325, 20, 2, 4, NULL, 0, 1);