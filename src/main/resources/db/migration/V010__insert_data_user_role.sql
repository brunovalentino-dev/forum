insert into forum_user (id, name, email, password) values (2, 'admin', 'admin@email.com', '$2a$12$Z8.naxfTaZsdzgUMm5awLOooFM5iaI9/AqgFDs3sevKljBS2Whgem');
insert into role (id, name) values (2, 'ADMIN');
insert into user_role (id, forum_user_id, role_id) values (2, 2, 2);