
create table checkouts (
  id int not null,
  total int not null,
  primary key(id)
);

update schema_info set version = 2;
