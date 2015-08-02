-- 1: Set Name
-- 2: Set Abbr
-- 3: Set Notes
-- 4: Where Focus Id

UPDATE set_focus
SET name=?, abbr=?, notes=?
WHERE focus_id=?;