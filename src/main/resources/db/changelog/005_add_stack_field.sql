ALTER TABLE projects
ADD COLUMN stack text;

UPDATE projects
SET stack = 'stack';