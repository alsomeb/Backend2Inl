-- create databases
create database customers;
create database orders;
create database items;


-- create tables
create table customers.customers (
                           id bigint not null,
                           created date,
                           first_name varchar(255),
                           last_name varchar(255),
                           last_updated date,
                           ssn varchar(12),
                           primary key (id)
) engine=InnoDB;

create table orders.orders (
                        id bigint not null,
                        customer_id bigint not null,
                        created date,
                        last_updated date,
                        primary key (id)
) engine=InnoDB;

create table items.items (
                       id bigint not null,
                       balance bigint,
                       created date,
                       img_data LONGBLOB,
                       last_updated date,
                       name varchar(255),
                       price bigint check (price>=1),
                       primary key (id)
) engine=InnoDB;


-- sequences
create sequence items.items_seq start with 7 increment by 1;

create sequence orders.orders_seq start with 5 increment by 1;

create sequence customers.customers_seq start with 4 increment by 1;