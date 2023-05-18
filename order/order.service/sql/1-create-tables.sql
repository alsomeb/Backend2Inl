create table orders (
                        id bigint not null,
                        customer_id bigint not null,
                        created date,
                        last_updated date,
                        primary key (id)
) engine=InnoDB;

create sequence orders_seq start with 5 increment by 1;