CREATE TABLE projects
(
    id            UUID primary key,
    owner_id      UUID not null,
    name          text not null,
    stage         text not null,
    about_project text,
    created_at    TIMESTAMP WITHOUT TIME ZONE
);

create table member_on_board
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    role       text
);

create table vacant_places
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    role       text
);

create table project_tag
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    name       text
);
