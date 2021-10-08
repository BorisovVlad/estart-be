set
search_path = public;

CREATE TABLE projects
(
    id            UUID primary key,
    owner_id      UUID not null,
    name          text not null,
    stage         text not null,
    about_project text,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    closed_at     TIMESTAMP WITHOUT TIME ZONE
);

create table member_on_board
(
    id         bigint primary key,
    project_id uuid references projects (id),
    role       text not null
);

create table vacant_places
(
    id         bigint primary key,
    project_id uuid references projects (id),
    role       text not null
);

create table project_tag
(
    id         bigint primary key,
    project_id uuid references projects (id),
    name       text not null
);
