CREATE TABLE user_table (
    id          UUID NOT NULL primary key ,
    first_name  text,
    last_name   text,
    email       text NOT NULL,
    password    text,
    about_me    text,
    hard_skills text,
    main_role   text
);

CREATE TABLE user_role (
    id         BIGINT primary key,
    user_id    UUID references user_table(id),
    name       text
);

CREATE TABLE user_tag (
    id         BIGINT primary key ,
    user_id    UUID references user_table(id),
    name       text
);