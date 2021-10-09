set search_path = public;

ALTER TABLE member_on_board ALTER COLUMN role
    drop not null;
ALTER TABLE vacant_places ALTER COLUMN role
    drop not null;
ALTER TABLE project_tag ALTER COLUMN name
    drop not null;