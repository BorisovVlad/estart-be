set
search_path = public;

CREATE TABLE projects
(
    id            UUID primary key,
    owner_id      UUID not null,
    stage         varchar(255) not null,
    about_project varchar(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    closed_at     TIMESTAMP WITHOUT TIME ZONE
);

create table member_on_board
(
    id         bigint primary key,
    project_id uuid references projects (id),
    role       varchar(255) not null
);

create table vacant_places
(
    id         bigint primary key,
    project_id uuid references projects (id),
    role       varchar(255) not null
);

create table project_tag
(
    id         bigint primary key,
    project_id uuid references projects (id),
    name       varchar(255) not null
);
