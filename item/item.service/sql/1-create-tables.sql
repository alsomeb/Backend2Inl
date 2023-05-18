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