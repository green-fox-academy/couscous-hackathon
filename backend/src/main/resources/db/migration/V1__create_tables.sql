CREATE TABLE user
(
    id       bigint       not null auto_increment,
    username varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255),
    primary key (id)
);