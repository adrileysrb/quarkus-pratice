create sequence nova_SEQ start with 1 increment by 50;
create table client (id smallint not null, city varchar(255) not null, country varchar(255) not null, email varchar(255) not null, first_name varchar(255) not null, gender varchar(255) not null, last_name varchar(255) not null, phone varchar(255) not null, postal_code varchar(255) not null, street_name varchar(255) not null, primary key (id));
create table nova (id bigint not null, name varchar(50) not null, bio varchar(3000) not null, primary key (id));
