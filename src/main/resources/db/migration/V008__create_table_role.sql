create table role (
    id bigint generated by default as identity not null,
    name varchar(50) not null,
    constraint pk_role primary key(id)
);

insert into role (id, name) values (1, 'READ_WRITE')
