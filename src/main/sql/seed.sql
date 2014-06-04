
insert into products (code, price) values ('A', '111');
insert into products (code, price) values ('B', '222');
insert into products (code, price) values ('C', '333');


CREATE EXTENSION pgcrypto;

insert into cashiers (id, encrypted_password) values ('123', digest('secret', 'sha256'));
insert into cashiers (id, encrypted_password) values ('456', digest('secret', 'sha256'));

