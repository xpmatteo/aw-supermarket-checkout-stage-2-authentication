
insert into products (code, price) values ('A', '111');
insert into products (code, price) values ('B', '222');
insert into products (code, price) values ('C', '333');

insert into checkouts (id, total) values (0, 0);
insert into checkouts (id, total) values (1, 100);

CREATE EXTENSION pgcrypto;

insert into cashiers (id, encrypted_password)
              values ('123', encode(digest('secret', 'sha256'), 'hex'));
insert into cashiers (id, encrypted_password)
              values ('456', encode(digest('very secret', 'sha256'), 'hex'));

