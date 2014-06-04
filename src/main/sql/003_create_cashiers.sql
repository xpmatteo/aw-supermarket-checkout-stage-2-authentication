
create table cashiers (
  id int not null,
  encrypted_password varchar(255) not null,
  primary key(id)
);

update schema_info set version = 3;

