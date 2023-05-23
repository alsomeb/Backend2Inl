GRANT ALL ON `customers`.* TO 'bengt'@'%';


USE customers;
-- Customers
create table customers (
                           id bigint not null,
                           created date,
                           first_name varchar(255),
                           last_name varchar(255),
                           last_updated date,
                           ssn varchar(12),
                           primary key (id)
) engine=InnoDB;
create sequence customers_seq start with 4 increment by 1;

insert into customers(id, first_name, last_name, ssn, created, last_updated)
values
    (1, 'Daniel', 'Fellden', '8505243241', current_timestamp(), current_timestamp()),
    (2, 'Alex', 'Brun', '5052412416', current_timestamp(), current_timestamp()),
    (3, 'Ceasar', 'Olle', '8505241241', current_timestamp(), current_timestamp());



CREATE DATABASE IF NOT EXISTS `orders`;
GRANT ALL ON `orders`.* TO 'bengt'@'%';
-- Orders
USE orders;
create table orders (
                        id bigint not null,
                        customer_id bigint not null,
                        created date,
                        last_updated date,
                        primary key (id)
) engine=InnoDB;

create sequence orders_seq start with 5 increment by 1;

insert into orders(id, customer_id, created, last_updated)
values
    (1, 1, current_timestamp(),  current_timestamp()),
    (2, 2, current_timestamp(),  current_timestamp()),
    (3, 2, current_timestamp(),  current_timestamp()),
    (4, 3, current_timestamp(),  current_timestamp());


CREATE DATABASE IF NOT EXISTS `items`;
GRANT ALL ON `items`.* TO 'bengt'@'%';
-- Items
USE items;
create table items (
                       id bigint not null,
                       balance bigint,
                       created date,
                       img_data LONGBLOB,
                       last_updated date,
                       name varchar(255),
                       price bigint check (price>=1),
                       primary key (id)
) engine=InnoDB;

create sequence items_seq start with 7 increment by 1;

insert into items(id, name, price, balance, created, last_updated)
values
    (1, 'banana', 20, 155, current_timestamp(), current_timestamp()),
    (2, 'apple', 25, 55, current_timestamp(), current_timestamp()),
    (3, 'orange', 10, 10, current_timestamp(), current_timestamp()),
    (4, 'pear', 15, 10, current_timestamp(), current_timestamp()),
    (5, 'macbook pro', 5000, 23, current_timestamp(), current_timestamp()),
    (6, 'Katalysator, universal', 629, 23, current_timestamp(), current_timestamp());




