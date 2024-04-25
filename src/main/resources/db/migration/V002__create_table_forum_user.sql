create table forum_user(
    id bigint generated by default as identity not null,
    name varchar(50),
    email varchar(50),
    constraint pk_forum_user primary key(id)
);

insert into forum_user (id, name, email) values (1, 'Ana da Silva', 'ana@email.com');