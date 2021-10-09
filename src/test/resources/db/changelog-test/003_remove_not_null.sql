alter table projects drop column closed_at;
alter table member_on_board alter column role drop not null;
alter table vacant_places alter column role drop not null;
alter table project_tag alter column name drop not null;

CREATE SEQUENCE member_on_board_id_seq START 1;
CREATE SEQUENCE vacant_places_id_seq START 1;
CREATE SEQUENCE project_tag_id_seq START 1;

alter table member_on_board alter column id set default nextval('member_on_board_id_seq');
alter table vacant_places alter column id set default nextval('vacant_places_id_seq');
alter table project_tag alter column id set default nextval('project_tag_id_seq');
