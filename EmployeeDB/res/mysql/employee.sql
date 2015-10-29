use wer00;
drop table if exists auction_list;
create table auction_list(
  empId int unsigned not null auto_increment,
  lastName varchar(24) not null,
  firstName varchar(18) not null,
  homePhone varchar(14) not null,
  salary double not null,
  productn int not null,
  auction int not null,
  primary key(empId)
);
