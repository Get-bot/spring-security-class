create table users
(
    username VARCHAR(50) not null primary key,
    password VARCHAR(500) not null,
    enabled  boolean not null
);

create table authorities
(
    username  VARCHAR(50) not null,
    authority VARCHAR(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

INSERT INTO users
VALUES ('happy', '12345', '1');
INSERT INTO authorities
VALUES ('happy', 'ADMIN');
