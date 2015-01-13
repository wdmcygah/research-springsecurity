--create table
create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null,
      salt varchar(25) not null
      );
create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);

create table groups (
  id bigint not null AUTO_INCREMENT  primary key, 
  group_name varchar(50) not null);

create table group_authorities (
  group_id bigint not null, 
  authority varchar(50) not null, 
  constraint fk_group_authorities_group foreign key(group_id) references groups(id));

create table group_members (
  id bigint  not null AUTO_INCREMENT  primary key, 
  username varchar(50) not null, 
  group_id bigint not null, 
  constraint fk_group_members_group foreign key(group_id) references groups(id));

create table persistent_logins (
 username varchar_ignorecase(50) not null,
 series varchar(64) primary key,
 token varchar(64) not null,
 last_used timestamp not null);
  
--init data
insert into users(username, password, enabled) values ('admin','admin',true);
insert into users(username, password, enabled) values ('guest','guest',true);
insert into authorities(username,authority) values ('admin','ROLE_USER');
insert into authorities(username,authority) values ('admin','ROLE_ADMIN');
insert into authorities(username,authority) values ('guest','ROLE_USER');
 
insert into groups(group_name) values ('Users');
insert into groups(group_name) values ('Administrators');
insert into group_authorities(group_id, authority) select id,'ROLE_USER' from groups where group_name='Users';
insert into group_authorities(group_id, authority) select id,'ROLE_USER' from groups where group_name='Administrators';
insert into group_authorities(group_id, authority) select id,'ROLE_ADMIN' from groups where group_name='Administrators';
insert into group_members(group_id, username) select id,'guest' from groups where group_name='Users';
insert into group_members(group_id, username) select id,'admin' from groups where group_name='Administrators';


