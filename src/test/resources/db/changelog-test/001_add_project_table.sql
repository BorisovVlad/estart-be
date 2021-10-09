CREATE TABLE projects
(
    id            UUID primary key,
    owner_id      UUID not null,
    stage         varchar(255) not null,
    about_project varchar(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE
);

create table member_on_board
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    role       varchar(255)
);

create table vacant_places
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    role       varchar(255)
);

create table project_tag
(
    id         bigserial primary key,
    project_id uuid references projects (id),
    name       varchar(255)
);
