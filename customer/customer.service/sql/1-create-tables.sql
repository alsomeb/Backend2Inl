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